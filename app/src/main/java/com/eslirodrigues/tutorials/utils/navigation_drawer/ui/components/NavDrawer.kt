package com.eslirodrigues.tutorials.utils.navigation_drawer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.utils.navigation_drawer.ui.navigation.NavDrawerRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    // Avoid use drawer with other nav components: bottomNav, tabNav, rail
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent()
        },
        gesturesEnabled = true,
        scrimColor = Color.Yellow
    ) {
        content()
    }
}


@Composable
fun DrawerContent(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    // Frequent navItems first and related together
    val itemsList = listOf(
        NavDrawerRoute.NavDrawerScreen,
        NavDrawerRoute.SecondDrawerScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalDrawerSheet {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_logo_foreground),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Text(
                text = "Tutorials",
                modifier = Modifier.padding(10.dp),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }

        itemsList.forEach { navDrawerItem ->
            NavigationDrawerItem(
                modifier = Modifier.padding(8.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = navDrawerItem.drawerIcon),
                        contentDescription = navDrawerItem.drawerName
                    )
                },
                badge = { Text(text = "1+") },
                label = {
                    Text(
                        text = navDrawerItem.drawerName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                selected = currentRoute == navDrawerItem.route,
                onClick = {
                    scope.launch { drawerState.close() }
                    if (navDrawerItem.route != currentRoute) {
                        navController.navigate(navDrawerItem.route)
                    }
                }
            )
        }
        HorizontalDivider(modifier = Modifier.padding(22.dp))
        Text(modifier = Modifier.padding(horizontal = 22.dp), text = "List")
        NavigationDrawerItem(
            modifier = Modifier.padding(8.dp),
            badge = { Text(text = "11+") },
            label = {
                Text(
                    text = "Item 1",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            selected = false,
            onClick = {}
        )
    }
}