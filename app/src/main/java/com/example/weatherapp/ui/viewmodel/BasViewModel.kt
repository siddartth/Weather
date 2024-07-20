package com.example.weatherapp.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.PriorityQueue


/**
 * Base class for view models. Creates all needed subjects for events and adds some dependencies
 */
abstract class BaseViewModel<OuterEvent : Any> : ViewModel(), DefaultLifecycleObserver {

    /**
     * State of the screen. Once loading is finished should be set to SUCCESS or ERROR
     */
    val state = MutableStateFlow(State.INIT)

    private var checkStateJob: Job? = null

    val isPageLoaded = MutableLiveData(true)
    val networkDisconnected = MutableLiveData(false)
    private val isSkeletonLoaderEnabled = ObservableBoolean(true)
    private var useSkeleton = false

    /**
     * Composite disposable for all disposables created while view model exists
     * Once onCleared method is called it is cleared
     */
    protected val disposable = CompositeDisposable()

    /**
     * Mutable shared flow for events that should be sent to attached fragment
     */
    protected val outerEvents = MutableSharedFlow<OuterEvent>(replay = 0)

    val outerEventsOld = PublishSubject.create<OuterEvent>()

    /**
     * SharedFlow that contains all events sent to the attached fragment
     */
    open val events: SharedFlow<OuterEvent> = outerEvents.asSharedFlow()

    open val eventsOld: io.reactivex.Observable<OuterEvent>? = outerEventsOld.hide()

    /**
     * PriorityQueue for events that should be delivered to fragment once it's available
     */
    protected val pendingEvents = PriorityQueue<OuterEvent>()

    private var viewModelCreatedTimestamp: Long = System.currentTimeMillis()

    private var screenLoadedEventSent = false
    private var showLoader = true

    private val freeMemoryOnStartup = getCurrentFreeMemory()

    private var requestTimestamp: Long = System.currentTimeMillis()

    private fun getCurrentFreeMemory() = Runtime.getRuntime().freeMemory()


    fun hideLoader(visible: Boolean = false) {
        showLoader = visible
    }

    fun showSkeleton(show: Boolean = true) {
        isSkeletonLoaderEnabled.set(show)
        useSkeleton = show
        if (show) {
            onLoading(false)
        }
    }

    val isSkeletonLoading = object : ObservableBoolean(isSkeletonLoaderEnabled) {
        override fun get(): Boolean {
            if (state.value != State.SUCCESS && state.value != State.ERROR) {
                useSkeleton = isSkeletonLoaderEnabled.get()
                if (useSkeleton) onLoading(false)
            }
            return isSkeletonLoaderEnabled.get()
        }
    }

    val isLoading: StateFlow<Boolean> = state.map { it == State.LOADING }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    val isError: StateFlow<Boolean> = state.map { it == State.ERROR }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    val isSuccess: StateFlow<Boolean> = state.map { it == State.SUCCESS }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    fun changeState(state: State) {
        this.state.value = state
    }

    fun showOnlyLoading(show: Boolean) {
        changeState(if (show) State.LOADING else State.SUCCESS)
    }

    init {
        checkStateJob = viewModelScope.launch {
            state.collect {
                if (state.value == State.SUCCESS || state.value == State.ERROR) {
                    useSkeleton = false
                    isSkeletonLoaderEnabled.set(false)
                }
                onLoading(state.value == State.LOADING && useSkeleton.not())
            }
        }.apply { start() }
    }

    /**
     * Can be overridden to listen changes when [State] is [State.LOADING]
     * Duplicate of property @see [isLoading], but can be used when [ObservableField]
     * property can not be used.
     * E.g. in [adp.auth.ui.signin.AuthViewModel] it used becaus of Jetpack Compose.
     * In Compose we can not use [ObservableField]
     */
    open fun onLoading(isLoading: Boolean) {
        if (showLoader) isPageLoaded.postValue(!isLoading)
    }

    override fun onCleared() {
        disposable.clear()
        checkStateJob?.cancel()
        super.onCleared()
    }

    protected open fun sendEvent(event: OuterEvent) {
        viewModelScope.launch {
            outerEvents.emit(event)
        }
        outerEventsOld.onNext(event)
    }

    enum class State {
        INIT,
        LOADING,
        SUCCESS,
        ERROR
    }

}
