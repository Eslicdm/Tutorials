package com.eslirodrigues.tutorials.input_mask.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialInputMaskScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Text(text = "Reverse", fontSize = 22.sp)
        TutorialReverseVisualTransformation()
        Text(text = "Char", fontSize = 22.sp)
        TutorialCharVisualTransformation()
        Text(text = "Manual", fontSize = 22.sp)
        TutorialManualVisualTransformation()
        Text(text = "Auto", fontSize = 22.sp)
        TutorialAutoVisualTransformation()
    }
}

// Ex: R$ 0,00| R$ 0,02| R$ 0,23| R$ 2,34|
@Composable
fun TutorialReverseVisualTransformation() {
    class ReverseVisualTransformation : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val inputText = text.text
            val inputTextLastIndex = inputText.lastIndex
            val formattedText = "${inputText[inputTextLastIndex - 2]}," + // "000123" "1,23"
                    "${inputText[inputTextLastIndex - 1]}${inputText[inputTextLastIndex]}"

            val offsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return 4 // "1,23" = 4
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return 3 // "123" = 3
                }
            }
            return TransformedText(AnnotatedString(formattedText) , offsetTranslator)
        }
    }

    var inputText by remember { mutableStateOf("000") }
    val inputLimit = 6 // "000123" "1,23"

    TextField(
        value = inputText,
        onValueChange = { currentText ->
            inputText = if (currentText.length < 4 || currentText.take(3) != "000") "000"
            else currentText.filter { it.isDigit() }.take(inputLimit)
        },
        label = { Text(text = "Reverse") },
        prefix = { Text(text = "R$ ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = ReverseVisualTransformation()
    )
}

@Composable
fun TutorialCharVisualTransformation() {
    var inputText by remember { mutableStateOf("") }
    PasswordVisualTransformation()
    TextField(
        value = inputText,
        onValueChange = { currentText -> inputText = currentText },
        label = { Text(text = "Char") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = { inputAnnotatedString ->
            TransformedText(
                text = AnnotatedString("$".repeat(inputAnnotatedString.text.length)),
                offsetMapping = OffsetMapping.Identity
            )
        }
    )
}


@Composable
fun TutorialManualVisualTransformation() {
    class ManualVisualTransformation(val enableCursorMove: Boolean) : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val inputText = text.text // "1111"
            // "(XX) XXXXX - XXXX"
            val formattedText = when(inputText.length) {
                in (1..2) -> "($inputText"
                in (3..7) -> "(${inputText.substring(0, 2)}) ${inputText.substring(2)}"
                in (8..11) ->  {
                    "(${inputText.substring(0, 2)}) ${inputText.substring(2, 7)} - ${inputText.substring(7)}"
                }
                else -> ""
            }

            val offsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // offset = input.count/original at cursor position -- "(11) 1|" offset = 3
                    val transformedCursor = when (offset) {
                        in (1..2) -> offset + 1 // "(XX|" offset = 2, sep = 1, result = 3
                        in (3..7) -> offset + 3 // "(XX) X|" offset = 3, sep = 3, result = 6
                        in (8..11) -> offset + 6
                        else -> offset
                    }

                    return if (enableCursorMove) transformedCursor // formattedText
                    else formattedText.length // "(XX) XXXXX - XXXX" return 17
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // offset = formatted.length/transformed at cursor position -- "(11) 1|" offset = 6
                    val originalCursor = when (offset) {
                        in (1..3) -> offset - 1 // "(XX|" offset = 3, sep = 1, result = 2
                        in (4..10) -> offset - 3 // "(XX) X|" offset = 6, sep = 3, result = 3
                        in (11..17) -> offset - 6
                        else -> offset
                    }

                    return if (enableCursorMove) originalCursor // inputs
                    else inputText.length // "(XX) XXXXX - XXXX" return 11
                }
            }
            return TransformedText(AnnotatedString(formattedText), offsetTranslator)
        }
    }

    var inputText by remember { mutableStateOf("") }
    val mask = "(XX) XXXXX - XXXX"
    val maskCharInput = 'X'
    val inputMaxLength = mask.count { maskChar -> maskChar == maskCharInput }

    TextField(
        value = inputText,
        onValueChange = { currentText ->
            inputText = currentText.take(inputMaxLength).filter { it.isDigit() }
        },
        label = { Text(text = "Manual") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = ManualVisualTransformation(enableCursorMove = true)
    )
}

@Composable
fun TutorialAutoVisualTransformation() {
    class AutoVisualTransformation(
        val mask: String,
        val maskCharInput: Char,
        val enableCursorMove: Boolean
    ) : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val inputText = text.text
            val formattedText = buildAnnotatedString {
                var separatorCount = 0
                var formattedTextLength = 0 // "(11) 1" input = 3, sep = 3, result = 6
                while (formattedTextLength < inputText.length + separatorCount) { // until 6
                    if (mask[formattedTextLength++] != maskCharInput) separatorCount++ // '(' != 'X'
                }
                var inputIndex = 0
                mask.take(formattedTextLength).forEach { maskCurrentChar -> // "(XX) XXXXX - XXXX"
                    if (maskCurrentChar != maskCharInput) append(maskCurrentChar) // '(', ')', ' '
                    else append(inputText[inputIndex++]) // '1','1','1'
                }
            }

            val offsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // offset = input.count/original at cursor position -- "(11) 1|" offset = 3
                    var separatorCount = 0 // "(11) 1" sep = 3
                    var transformedCursor = 0 // inputs + separators = formatted.length using offset
                    while (transformedCursor < offset + separatorCount) { // "(11) 1" until 6
                        if (mask[transformedCursor++] != maskCharInput) separatorCount++ // '(' != 'X'
                    }
                    return if (enableCursorMove) transformedCursor // "(11) 1|" offset/input + sep = 6
                    else formattedText.length // "(XX) XXXXX - XXXX" return 17
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // offset = formatted.count/transformed at cursor position -- "(11) 1|" offset = 6
                    return if (enableCursorMove) {
                        val sepCount = mask.take(offset).count { it != maskCharInput } // "() " sep = 3
                        offset - sepCount // originalCursor/inputs "(11) 1|" offset/form.length - sep = 3
                    } else inputText.length // "(XX) XXXXX - XXXX" return 11
                }
            }
            return TransformedText(formattedText, offsetTranslator)
        }
    }

    var inputText by remember { mutableStateOf("") }
    val mask = "(XX) XXXXX - XXXX"
    val maskCharInput = 'X'
    val inputMaxLength = mask.count { maskChar -> maskChar == maskCharInput }

    TextField(
        value = inputText,
        onValueChange = { currentText ->
            inputText = currentText.take(inputMaxLength).filter { it.isDigit() }
        },
        label = { Text(text = "Auto") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = AutoVisualTransformation(
            mask = mask,
            maskCharInput = maskCharInput,
            enableCursorMove = false
        )
    )
}
