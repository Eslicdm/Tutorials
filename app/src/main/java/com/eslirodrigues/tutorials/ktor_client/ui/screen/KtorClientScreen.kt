package com.eslirodrigues.tutorials.ktor_client.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import com.eslirodrigues.tutorials.ktor_client.ui.viewmodel.KtorClientViewModel

@Composable
fun KtorClientScreen(
    viewModel: KtorClientViewModel = hiltViewModel()
) {
    val itemState by viewModel.itemState.collectAsStateWithLifecycle()
    val productState by viewModel.productState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when {
            itemState.isLoading -> CircularProgressIndicator()
            itemState.errorMsg != null -> Text(text = itemState.errorMsg ?: "Error")
            itemState.item != null -> {
                Text(text = itemState.item?.authorName ?: "0")
            }
        }
        Button(onClick = { viewModel.getItemFromApi() }) {
            Text(text = "Get Author")
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))

        when {
            productState.isLoading -> CircularProgressIndicator()
            productState.errorMsg != null -> Text(text = productState.errorMsg ?: "Error")
            productState.product != null -> {
                Text(text = productState.product?.name ?: "0")
                Text(text = productState.product?.id ?: "0")
            }
        }
        Button(onClick = {
            viewModel.createProduct(productPost = KtorClientProductPost(name = "MYPOST"))
        }) {
            Text(text = "Create Product")
        }
        var id by remember { mutableStateOf("") }
        TextField(value = id, onValueChange = { id = it })
        Button(onClick = { viewModel.deleteProductById(id = id) }) {
            Text(text = "Delete Product")
        }
    }
}