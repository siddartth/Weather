package com.example.weatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.domain.data.WeatherValue
import com.example.weatherapp.ui.support.AppTheme
import com.example.weatherapp.ui.support.SearchBar
import com.example.weatherapp.ui.support.SearchBarViewState
import com.example.weatherapp.ui.support.getShimmerBrush
import com.example.weatherapp.ui.support.rememberImagePainter
import com.example.weatherapp.ui.viewmodel.BaseViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
internal fun WeatherLandingScreen(
    viewModel: WeatherViewModel
) {
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF204D82),
            Color(0xFF204D82),
            Color(0xFF80B1B1),
            Color(0xFFDCEFFD)
        )
    )

    val state by viewModel.state.collectAsState()

    val newData = viewModel.weather.observeAsState()
    val isApiError = viewModel.isApiError.observeAsState()
    val errorMessage = viewModel.apiErrorMessage.observeAsState()
    val locationName = newData.value?.name
    val currentTime = Calendar.getInstance().time
    val localTimeFormater = SimpleDateFormat("MMM, dd yyyy - HH:mm", Locale.US)
    val localTime = localTimeFormater.format(currentTime)
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .background(gradient)
        ) {
            paddingValues ->
            Column(
            modifier = Modifier
                .background(gradient)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
                SearchBarView(viewModel.searchBarViewState)
                Spacer(
                    modifier = Modifier
                        .padding(top = 64.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = "$localTime PST",
                    style = AppTheme.textAppearance.bodyNormal,
                    color = AppTheme.colors.redesignWhite
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .wrapContentHeight(),
                    text = "United States,",
                    style = AppTheme.textAppearance.redesignH1,
                    color = AppTheme.colors.redesignWhite
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .wrapContentHeight(),
                    text = locationName.orEmpty(),
                    style = AppTheme.textAppearance.redesignBodyLarge,
                    color = AppTheme.colors.redesignWhite
                )
                when (state) {
                    BaseViewModel.State.SUCCESS -> {
                        WeatherCard(newData)
                    }
                    BaseViewModel.State.LOADING -> {
                        WeatherCarSkeletonLoader()
                    }
                    BaseViewModel.State.ERROR -> {
                        WeatherCardErrorState(errorMessage.value.orEmpty())
                    }
                    else -> {WeatherCardErrorState(errorMessage.value.orEmpty())}
                }
            }
        }
    }
}


@Composable
internal fun SearchBarView(
    searchBarViewState: SearchBarViewState
) {
    Spacer(
        modifier = Modifier
            .padding(top = 24.dp)
    )
    SearchBar(
        modifier = Modifier
            .padding(
                horizontal = 16.dp
            ),
        viewState = searchBarViewState,
        isCenterAlign = false
    )
}


@Composable
internal fun WeatherCardErrorState(
    errorMessage: String
) {
    val brush = getShimmerBrush(colorResource(id = R.color.blue_menu))
    Spacer(
        modifier = Modifier
            .padding(top = 24.dp)
    )
    Column(
        modifier = Modifier
            .background(brush)
            .fillMaxWidth()
            .height(448.dp)
            .padding(32.dp)
            .shadow(
                elevation = 8.dp,
                RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentHeight(),
            text = "$errorMessage \n Sorry for the inconvenience \n Please check the city name and re-try.",
            style = AppTheme.textAppearance.redesignH2,
            color = AppTheme.colors.redesignWhite
        )
    }

}

@Composable
internal fun WeatherCarSkeletonLoader(
) {
    val brush = getShimmerBrush(colorResource(id = R.color.blue_menu))
    Spacer(
        modifier = Modifier
            .padding(top = 24.dp)
    )
    Column(
        modifier = Modifier
            .background(brush)
            .fillMaxWidth()
            .height(448.dp)
            .padding(32.dp)
            .shadow(
                elevation = 8.dp,
                RoundedCornerShape(16.dp)
            )
    ) {}
}

@Composable
internal fun WeatherCard(weather: State<WeatherValue?>) {
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF204D82),
            Color(0xFF204D82),
            Color(0xFF80B1B1),
            Color(0xFFDCEFFD)
        )
    )

    val whiteBorder = Brush.radialGradient(
        listOf(
            Color(0xFFDCEFFD),
            Color(0xFFDCEFFD)
        )
    )
    val iconValue = weather.value?.weather?.get(0)?.icon
    val imageUrl: String = if (!iconValue.isNullOrEmpty())
    {
        "https://openweathermap.org/img/wn/${iconValue}@2x.png"
    } else ""
    val temp = (weather.value?.main?.temp?.toInt()?.minus(264)).toString()
    val humidity = weather.value?.main?.humidity
    val wind = weather.value?.wind?.speed
    val description = weather.value?.weather?.get(0)?.description
    val lowTemp = weather.value?.main?.tempMin?.toInt()?.minus(264).toString()
    val noImage = ContextCompat.getDrawable(
        LocalContext.current,
        R.drawable.ic_loading_image
    )

    Spacer(
        modifier = Modifier
            .padding(top = 24.dp)
    )
    Column(
        modifier = Modifier
            .background(gradient)
            .fillMaxWidth()
            .height(448.dp)
            .padding(32.dp)
            .shadow(
                elevation = 8.dp,
                RoundedCornerShape(16.dp)
            )
    ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 48.dp)
            )
        Row {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(112.dp)
                    .height(112.dp),
                painter = rememberImagePainter(
                    imageUrl = imageUrl,
                    errorImage = noImage,
                    emptyUrlImage = noImage
                ),
                contentDescription = stringResource(R.string.next)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .wrapContentHeight(),
                text = temp.orEmpty(),
                style = AppTheme.textAppearance.redesignH4,
                color = AppTheme.colors.redesignWhite
            )
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = "\u2103",
                style = AppTheme.textAppearance.redesignH1,
                color = AppTheme.colors.redesignWhite
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .wrapContentHeight(),
                text = "| F",
                style = AppTheme.textAppearance.redesignH1,
                color = AppTheme.colors.redesignWhite
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 62.dp)
                .wrapContentHeight(),
            text = "Expected ${description}.\n The low will be ${lowTemp} \u2103",
            style = AppTheme.textAppearance.bodyNormal,
            color = AppTheme.colors.redesignWhite
        )
        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .wrapContentWidth()
                    .border(
                        brush = whiteBorder,
                        width = 2.dp,
                        shape = RoundedCornerShape(5.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Humidity",
                    style = AppTheme.textAppearance.bodyNormal,
                    color = AppTheme.colors.redesignWhite
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight(),
                    text = "${humidity}%",
                    style = AppTheme.textAppearance.redesignBodyLarge,
                    color = AppTheme.colors.redesignWhite
                )
            }
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .wrapContentSize()
                    .border(
                        brush = whiteBorder,
                        width = 2.dp,
                        shape = RoundedCornerShape(5.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentHeight(),
                    text = "Wind",
                    style = AppTheme.textAppearance.bodyNormal,
                    color = AppTheme.colors.redesignWhite
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight(),
                    text = "${wind}Km",
                    style = AppTheme.textAppearance.redesignBodyLarge,
                    color = AppTheme.colors.redesignWhite
                )
            }
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .wrapContentSize()
                    .border(
                        brush = whiteBorder,
                        width = 2.dp,
                        shape = RoundedCornerShape(5.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentHeight(),
                    text = "Temp",
                    style = AppTheme.textAppearance.bodyNormal,
                    color = AppTheme.colors.redesignWhite
                )
                Text(
                    modifier = Modifier
                        .padding(14.dp)
                        .wrapContentHeight(),
                    text = "$temp\u2103",
                    style = AppTheme.textAppearance.redesignBodyLarge,
                    color = AppTheme.colors.redesignWhite
                )
            }
        }
    }
    Spacer(
        modifier = Modifier
            .padding(top = 48.dp)
    )
}