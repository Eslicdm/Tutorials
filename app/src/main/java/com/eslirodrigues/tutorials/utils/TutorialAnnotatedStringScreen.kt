package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialAnnotatedStringScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        TutorialAnnotatedTextWithLinks()
        TutorialAnnotatedExpandableText()
    }
}

@Composable
fun TutorialAnnotatedTextWithLinks() {
    val textsAndLinks = mutableMapOf(
        "Main Page" to "https://github.com/Eslicdm/Tutorials",
        "Commits" to "https://github.com/Eslicdm/Tutorials/commits/master"
    )
    val fullText = "In the Github Tutorials: Main Page and Commits you can find the code."

    val uriHandler = LocalUriHandler.current

    val annotatedTextWithLink = buildAnnotatedString {
        withStyle(SpanStyle(fontSize = 30.sp)) { append(fullText) }
        textsAndLinks.forEach { (linkText, link) ->
            val startIndex = fullText.indexOf(linkText)
            val endIndex = startIndex + linkText.length
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(tag = "URL", annotation = link, start = startIndex, end = endIndex)
        }
    }
    ClickableText(text = annotatedTextWithLink, onClick = { offset ->
        annotatedTextWithLink.getStringAnnotations(
            tag = "URL", start = offset, end = offset
        ).firstOrNull()?.let { uriHandler.openUri(it.item) }
    })
}

@Composable
fun TutorialAnnotatedExpandableText() {
    val fullText = "In the Tutorials you can find all the codes, you can search in folders or commits"
    val readLessText = "... Read less"
    val readMoreText = "... Read more"
    val maxLines = 1

    var isTextExceedingLines by remember { mutableStateOf(false) }
    var lastVisibleTextIndex by remember { mutableIntStateOf(0) }
    var isExpanded by remember { mutableStateOf(false) }

    val adjustedText = if (isTextExceedingLines)
        fullText.substring(0, lastVisibleTextIndex - readMoreText.length) else fullText

    val annotatedExpandableText = buildAnnotatedString {
        withStyle(SpanStyle(fontSize = 30.sp)) {
            append(if (isExpanded) fullText else adjustedText)
            pushStringAnnotation(tag = "less", annotation = readLessText)
            pushStringAnnotation(tag = "more", annotation = readMoreText)
            withStyle(SpanStyle(color = if (isExpanded) Color.Red else Color.Green)) {
                append(if (isExpanded) readLessText else readMoreText)
            }
            pop()
        }
    }
    ClickableText(
        text = annotatedExpandableText,
        maxLines = if (!isExpanded) 1 else Int.MAX_VALUE,
        onTextLayout = { layout ->
            isTextExceedingLines = layout.layoutInput.softWrap
            lastVisibleTextIndex = layout.getLineEnd(maxLines - 1)
        },
        onClick = { offset ->
            when (annotatedExpandableText.getStringAnnotations(offset, offset).lastOrNull()?.tag) {
                "less", "more"  -> isExpanded = !isExpanded
            }
        }
    )
}