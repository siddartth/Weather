package com.example.weatherapp.ui.support

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData


data class SearchBarViewState(
    val onRootClick: (() -> Unit)? = null,
    var isCursorVisible: MutableLiveData<Boolean> = MutableLiveData(false),
    var query: MutableLiveData<String>? = MutableLiveData(""),
    var submitQuery: (() -> Unit)? = null,
    var onCancelClick: (() -> Unit)? = null,
    var showCancelButton: MutableLiveData<Boolean> = MutableLiveData(false),
    var setEditMode: MutableLiveData<Boolean> = MutableLiveData(true),
    var hint: MutableLiveData<String> = MutableLiveData(),
    var imeOptions: MutableLiveData<Int> = MutableLiveData(EditorInfo.IME_ACTION_SEARCH),
    val imeAction: ((String) -> Unit)? = { submitQuery?.invoke() },
    val onSearchBarInit: ((View) -> Unit)? = null,
    val autoFocus: Boolean = false,
    val showClearQueryIcon: Boolean = true
) {
    val cursorPosition = ObservableInt()

    fun clearQuery() {
        query?.postValue("")
    }

    fun moveCursorToEnd() {
        query?.value?.let {
            cursorPosition.set(it.length)
        }
    }
}