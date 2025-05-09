package com.eslirodrigues.tutorials.utils

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
        Text(text = "Char", fontSize = 22.sp)
        TutorialCharVisualTransformation()
        Text(text = "Manual", fontSize = 22.sp)
        TutorialManualVisualTransformation()
        Text(text = "Auto", fontSize = 22.sp)
        TutorialAutoVisualTransformation()
        Text(text = "Reverse", fontSize = 22.sp)
        TutorialReverseVisualTransformation()
        Text(text = "Currency Conversion", fontSize = 22.sp)
        TutorialCurrencyConversionVisualTransformation()
    }
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
                        in (8..11) -> offset + 6 // 17 - 6 = 11
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
                        in (11..17) -> offset - 6 // 11 + 8 = 17
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

// "Bug" - When click delete a lot of times, we need to add a number 4 times
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
                override fun originalToTransformed(offset: Int): Int = 4 // "1,23" = 4
                override fun transformedToOriginal(offset: Int): Int = 3 // "123" = 3
            }
            return TransformedText(AnnotatedString(formattedText), offsetTranslator)
        }
    }

    var inputText by remember { mutableStateOf("000") }
    val inputLimit = 6 // "000123" "1,23"

    TextField(
        value = inputText,
        onValueChange = { currentText ->
            inputText = if (currentText.take(3) != "000") "000"
            else currentText.filter { it.isDigit() }.take(inputLimit)
        },
        label = { Text(text = "Reverse") },
        prefix = { Text(text = "R$ ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ReverseVisualTransformation()
    )
}

@Composable
fun TutorialCurrencyConversionVisualTransformation() {
    class CurrencyConversionVisualTransformation(val enableCursorMove: Boolean) : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val inputText = text.text
            val formattedText = buildAnnotatedString {
                inputText.reversed().forEachIndexed { index, c ->
                    when (index) {
                        5, 8, 11, 14, 17, 20, 23, 26, 29 -> append(".$c")
                        1 -> append("$c,")
                        else -> append(c)
                    }
                }
            }.reversed().toString()

            // the basic logic is offset + separator.
            // "Bug" - sometime the cursor jump 2 chars when user is moving (cursorEnabled)
            val offsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val transformedCursor = when (offset) { // 111.111.111.111.111.111.111.111.111.111,00
                        in 2..4 -> offset + 1
                        in 6..8 -> offset + 2
                        in 9..11 -> offset + 3
                        in 12..14 -> offset + 4
                        in 15..17 -> offset + 5
                        in 18..20 -> offset + 6
                        in 21..23 -> offset + 7
                        in 24..26 -> offset + 8
                        in 27..29 -> offset + 9 // 38 - 9 = 29
                        else -> offset
                    }
                    return if (enableCursorMove) transformedCursor else formattedText.length
                }

                // the basic logic is offset - separator.
                // "Bug" - sometime the cursor jump 2 chars when user is moving (cursorEnabled)
                override fun transformedToOriginal(offset: Int): Int {
                    val originalCursor = when (offset) { // 111.111.111.111.111.111.111.111.111.111,00
                        in 2..6 -> offset - 1
                        in 7..10 -> offset - 2
                        in 11..14 -> offset - 3
                        in 15..18 -> offset - 4
                        in 19..22 -> offset - 5
                        in 23..26 -> offset - 6
                        in 27..30 -> offset - 7
                        in 31..34 -> offset - 8
                        in 35..38 -> offset - 9 // 29 + 9 = 38
                        else -> offset
                    }
                    return if (enableCursorMove) originalCursor else inputText.length
                }
            }
            return TransformedText(AnnotatedString(formattedText), offsetTranslator)
        }
    }

    // "Bug" - When delete the "000" the cursor moves, the cursor should be in a fixed position
    // that's why enableCursorMove is used in the inputText, so users can remove chars in wrong positions
    // Maybe is not a good idea yet to let the cursor move and have prefixed values like "000"
    val enableCursorMove = false
    var inputText by remember { mutableStateOf(if (enableCursorMove) "000" else "") }
    val inputLimit = 29 // 111.111.111.111.111.111.111.111.111.111,00

    TextField(
        value = inputText,
        onValueChange = { currentText ->
            inputText = if (currentText.length < 3 && enableCursorMove) "000"
            else currentText.filter { it.isDigit() }.take(inputLimit)
        },
        label = { Text(text = "Currency Conversion") },
        prefix = { Text(text = "R$ ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CurrencyConversionVisualTransformation(enableCursorMove)
    )
}