package com.eslirodrigues.tutorials.stickyheader_lazycolumn.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.stickyheader_lazycolumn.data.model.NameAge

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaderScreen() {
    val nameAgeList = getNameAge()

    LazyColumn(Modifier.fillMaxSize().padding(10.dp)) {
        val listGroup = nameAgeList.groupBy { item -> item.age }

        listGroup.forEach { (header, groupItems) ->
            stickyHeader {
                Text(
                    text = header,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(10.dp)
                )
            }
            items(groupItems) { nameAge ->
                Text(text = "Name: ${nameAge.name} - Age: ${nameAge.age}")
            }
        }
    }
}

fun getNameAge(): List<NameAge> {
    val list = mutableListOf<NameAge>()
    repeat(20) {
        list.add(NameAge(name = "Josh", age = "22"))
        list.add(NameAge(name = "Nick", age = "22"))
        list.add(NameAge(name = "Rob", age = "33"))
        list.add(NameAge(name = "John", age = "33"))
        list.add(NameAge(name = "Richard", age = "44"))
        list.add(NameAge(name = "Julia", age = "44"))
    }
    return list
}