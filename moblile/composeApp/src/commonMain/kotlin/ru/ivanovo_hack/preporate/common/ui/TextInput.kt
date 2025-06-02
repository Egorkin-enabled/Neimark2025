package ru.ivanovo_hack.preporate.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import preporate.composeapp.generated.resources.*
import preporate.composeapp.generated.resources.Res

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textInputLabel: String? = null,
    secureText: Boolean = false,
    onValueChanged: (String) -> Unit,
    value: String,
    isError: Boolean = false,
    supportingValue: String = "",
) {

    var text by remember {
        mutableStateOf(value)
    }

    var isHideText by remember {
        mutableStateOf(secureText)
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
        ),
        label = {
            textInputLabel?.let {
                Text(
                    text = textInputLabel,
                    modifier = Modifier
                        .background(Color.Transparent),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                    )
                )
            }
        },
        singleLine = true,
        onValueChange = {
            text = it
            onValueChanged(text)
        },
        trailingIcon = {
            if (secureText) {
                IconButton(onClick = { isHideText = !isHideText }) {
                    Icon(
                        painter = painterResource(
                            resource = if (isHideText) Res.drawable.baseline_visibility_24
                            else Res.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = ""
                    )
                }
            }
        },
        shape = RoundedCornerShape(size = 12.dp),
        visualTransformation = if (secureText && isHideText) PasswordVisualTransformation() else
            VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = {
            if(supportingValue.isNotEmpty()){
                Text(
                    text = supportingValue,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(200),
                    )
                )
            }
        },
        isError = isError,
    )
}

@Preview
@Composable
internal fun TextInput_PreView(){
    var value by remember {
        mutableStateOf("Test value")
    }

    TextInput(
        value = value,
        onValueChanged = {
            value = it
        }
    )
}