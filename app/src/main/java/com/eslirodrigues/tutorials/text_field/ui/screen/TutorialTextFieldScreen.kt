package com.eslirodrigues.tutorials.text_field.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TutorialTextFieldScreen() {
    var textField by remember { mutableStateOf("") }
    var outlinedTextField by remember { mutableStateOf("") }
    var basicTextField by remember { mutableStateOf("Basic Text") }

    var isError by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Text(text = "BasicTextField", fontSize = 22.sp)
        BasicTextField(
            value = basicTextField,
            onValueChange = { basicTextField = it },
            enabled = true,
            maxLines = 2,
            minLines = 1,
            readOnly = true
        )

        Text(text = "TextField", fontSize = 22.sp)
        TextField(
            value = textField,
            onValueChange = {userInput ->
                textField = userInput
                isError = textField.length <= 2
            },
            label = { Text(text = "Label") },
            singleLine = true,
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    if(showPassword) {
                        Icon(Icons.Default.Visibility, contentDescription = "Visibility On")
                    } else Icon(Icons.Default.VisibilityOff, contentDescription = "Visibility Off")
                }
            },
            isError = isError,
            supportingText = { if (isError) Text(text = "Error Message - Supporting Text") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        Text(text = "OutlinedTextField", fontSize = 22.sp)
        OutlinedTextField(
            value = outlinedTextField,
            onValueChange = { outlinedTextField = it },
            placeholder = { Text(text = "Placeholder") },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = "Edit") },
            prefix = { Text(text = "Prefix") },
            suffix = { Text(text = "Suffix") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(onSend = {
//                keyboardController?.hide()
                focusManager.clearFocus()
            })
        )
    }
}