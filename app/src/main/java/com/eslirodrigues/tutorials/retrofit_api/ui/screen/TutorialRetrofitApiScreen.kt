package com.eslirodrigues.tutorials.retrofit_api.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import com.eslirodrigues.tutorials.retrofit_api.ui.viewmodel.RetrofitApiViewModel

@Composable
fun TutorialRetrofitApiScreen(
    viewModel: RetrofitApiViewModel = hiltViewModel()
) {
    val itemState by viewModel.itemState.collectAsState()
    val productState by viewModel.productState.collectAsState()

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
            viewModel.createProduct(productPost = RetrofitApiProductPost(name = "MYPOST"))
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