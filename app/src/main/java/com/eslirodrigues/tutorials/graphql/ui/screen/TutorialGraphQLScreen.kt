package com.eslirodrigues.tutorials.graphql.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.graphql.ui.viewmodel.TutorialGraphQLViewModel

@Composable
fun TutorialGraphQLScreen(
    graphQLViewModel: TutorialGraphQLViewModel = hiltViewModel(),
) {
    val pokeState by graphQLViewModel.pokeState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        when {
            pokeState.isLoading -> CircularProgressIndicator()
            !pokeState.errorMsg.isNullOrEmpty() -> Text(pokeState.errorMsg!!)
            pokeState.data.isNullOrEmpty() -> Text(text = "Empty")
            !pokeState.data.isNullOrEmpty() -> {
                LazyColumn {
                    items(pokeState.data!!) { species ->
                        Text(text = "Name: ${species.name} \n Id: ${species.id}")
                    }
                }
            }
        }
    }
}