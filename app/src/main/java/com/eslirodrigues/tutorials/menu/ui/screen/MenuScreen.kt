package com.eslirodrigues.tutorials.menu.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen() {
    Column(Modifier.fillMaxSize()) {
        TutorialTopMenu()
        Spacer(modifier = Modifier.padding(20.dp))

        Text(text = "Exposed Menu", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        TutorialExposedMenu()
        Spacer(modifier = Modifier.padding(20.dp))


        Text(text = "Exposed Filter Menu", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        TutorialExposedFilterMenu()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialTopMenu() {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Tutorial Menu") },
        actions = {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "menu")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Add") },
                    onClick = { },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Add,
                            contentDescription = "Add"
                        )
                    }
                )
                DropdownMenuItem(
                    enabled = false,
                    text = { Text("Delete") },
                    onClick = { },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = "Delete"
                        )
                    }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Share") },
                    onClick = { },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Share,
                            contentDescription = "Share"
                        )
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialExposedMenu() {
    val carList = listOf("Ferrari", "Porsche", "BMW")
    var selectedCar by remember { mutableStateOf(carList[0]) }
    var showMenu by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = showMenu,
        onExpandedChange = { showMenu = !showMenu },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedCar,
            onValueChange = { },
            label = { Text("Car") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = showMenu)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedTextColor = if (showMenu) Color.LightGray else Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
        ) {
            carList.forEach { car ->
                DropdownMenuItem(
                    text = { Text(car) },
                    onClick = {
                        selectedCar = car
                        showMenu = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialExposedFilterMenu() {
    val animalList = remember { mutableStateListOf("Dog", "Cat", "Human", "Snake") }
    var selectedAnimal by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = showMenu,
        onExpandedChange = { showMenu = !showMenu },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedAnimal,
            onValueChange = { selectedAnimal = it },
            label = { Text("Animal") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = showMenu)
            }
        )

        val animalListFilter = animalList.filter {
            it.contains(selectedAnimal, ignoreCase = true)
        }
        if (animalListFilter.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
            ) {
                animalListFilter.forEach { animal ->
                    DropdownMenuItem(
                        text = { Text(animal) },
                        onClick = {
                            selectedAnimal = animal
                            showMenu = false
                        }
                    )
                }
            }
        }
    }
}