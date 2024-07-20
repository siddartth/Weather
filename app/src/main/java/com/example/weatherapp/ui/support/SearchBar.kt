package com.example.weatherapp.ui.support

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_GO
import android.view.inputmethod.EditorInfo.IME_ACTION_NEXT
import android.view.inputmethod.EditorInfo.IME_ACTION_PREVIOUS
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewState: SearchBarViewState,
    isCenterAlign: Boolean = true,
    @DrawableRes searchIcon: Int = R.drawable.ic_search_m_default_dark,
    @DrawableRes clearIcon: Int = R.drawable.ic_clear_m_default_dark,
    @StringRes clearIconDescription: Int = R.string.content_description_clear_search,
) {
    val focusRequester = if (viewState.autoFocus) {
        remember { FocusRequester() }
    } else {
        null
    }
    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickableWithoutRipple {
                viewState.onRootClick?.invoke()
            }
            .padding(
                vertical = dimensionResource(
                    id = if (isCenterAlign) {
                        R.dimen.normal_10
                    } else {
                        R.dimen.normal_0
                    }
                ),
                horizontal = dimensionResource(id = R.dimen.normal_10)
            ),
        verticalAlignment = if (isCenterAlign) {
            Alignment.CenterVertically
        } else {
            Alignment.Top
        }
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(dimensionResource(id = R.dimen.normal_50))
                .clip(
                    RoundedCornerShape(50)
                )
                .background(
                    color = AppTheme.colors.redesignHover
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.normal_30)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.normal_20))
        ) {
            Icon(
                modifier = Modifier,
                painter = painterResource(id = searchIcon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            val interactionSource = remember { MutableInteractionSource() }
            val textValue = viewState.query?.observeAsState()?.value.orEmpty()
            val enabled = viewState.setEditMode.observeAsState().value

            // modifier should be create this way, using if else condition
            // using only instance of Modifier.weight(1f) and then applying to it
            // focusRequester doesn't work and crash the app
            val textFieldModifier = if (focusRequester == null) {
                Modifier.weight(5f)
            } else {
                Modifier
                    .focusRequester(focusRequester)
            }
            if (enabled != null) {
                BasicTextField(
                    modifier = textFieldModifier,
                    value = textValue,
                    keyboardOptions = KeyboardOptions(
                        imeAction = viewState.imeOptions.observeAsState().value.toComposeImeAction()
                    ),
                    keyboardActions = KeyboardActions {
                        viewState.submitQuery?.invoke()
                    },
                    onValueChange = {
                        viewState.query?.value = it
                    },
                    singleLine = true,
                    interactionSource = interactionSource,
                    enabled = enabled,
                    cursorBrush = SolidColor(
                        if (viewState.isCursorVisible.observeAsState().value == true) {
                            AppTheme.colors.redesignBlack
                        } else {
                            Color.Transparent
                        }
                    ),
                    decorationBox = {
                        TextFieldDefaults.OutlinedTextFieldDecorationBox(
                            value = textValue,
                            innerTextField = it,
                            enabled = false,
                            interactionSource = interactionSource,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            contentPadding = PaddingValues(20.dp),
                            placeholder = {
                                viewState.hint.observeAsState().value?.let { textBuilder ->
                                    Text(
                                        text = textBuilder.toString(),
                                        style = AppTheme.textAppearance.bodyNormal.copy(
                                            color = AppTheme.colors.redesignGray1
                                        ),
                                        maxLines = 1
                                    )
                                }
                            }
                        )
                    }
                )
            }

            if (!viewState.query?.observeAsState()?.value.isNullOrEmpty()) {
                Icon(
                    modifier = Modifier
                        .clickableWithoutRipple {
                            viewState.clearQuery()
                        },
                    painter = painterResource(id = clearIcon),
                    contentDescription = stringResource(
                        id = clearIconDescription
                    ),
                    tint = Color.Unspecified
                )
            }


            val context = LocalContext.current

            if (viewState.showCancelButton.observeAsState().value == true) {
                Text(
                    modifier = Modifier
                        .clickableWithoutRipple {
                            viewState.onCancelClick?.invoke()
                        }
                        .padding(
                            start = dimensionResource(id = R.dimen.normal_20)
                        )
                        .semantics {
                            contentDescription = R.string.content_description_search_cancel
                                .toString()
                        },
                    text = stringResource(id = R.string.cancel),
                    style = AppTheme.textAppearance.bodyNormal.copy(
                        color = AppTheme.colors.redesignGray1
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        focusRequester?.let {
            LaunchedEffect(Unit) {
                it.requestFocus()
            }
        }
    }
}

/**
 * [clickable][androidx.compose.foundation.clickable] without the ripple
 *
 * @param onClick will be called when user clicks on the element
 */
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    )
}

/**
 * This function matches xml [EditorInfo] ime action to compose [ImeAction]
 */
internal fun Int?.toComposeImeAction() = when (this) {
    IME_ACTION_SEARCH -> ImeAction.Search
    IME_ACTION_DONE -> ImeAction.Done
    IME_ACTION_NEXT -> ImeAction.Next
    IME_ACTION_PREVIOUS -> ImeAction.Previous
    IME_ACTION_SEND -> ImeAction.Send
    IME_ACTION_GO -> ImeAction.Go
    else -> ImeAction.None
}
