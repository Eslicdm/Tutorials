package com.eslirodrigues.tutorials.navigation_drawer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.eslirodrigues.tutorials.navigation_drawer.ui.navigation.NavDrawerRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    // Avoid use drawer with other nav components: bottomNav, tabNav, rail
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent()
        },
        gesturesEnabled = true,
        drawerContainerColor = Color.Red,
        drawerContentColor = Color.Green,
        scrimColor = Color.Yellow
    ) {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(12.dp),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        itemsList.forEach { navDrawerItem ->
            val backgroundColor = if (navDrawerItem.route == currentRoute) Color.DarkGray else Color.Transparent
            val textIconColor = if (navDrawerItem.route == currentRoute) MaterialTheme.colorScheme.primary else Color.LightGray
            TextButton(
                modifier = Modifier
                    .background(backgroundColor, shape = CircleShape)
                    .padding(vertical = 8.dp),
                onClick = {
                    scope.launch { drawerState.close() }
                    if (navDrawerItem.route != currentRoute) {
                        navController.navigate(navDrawerItem.route)
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = navDrawerItem.drawerIcon),
                    contentDescription = navDrawerItem.drawerName,
                    tint = textIconColor
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Text(
                    text = navDrawerItem.drawerName,
                    fontSize = 16.sp, color = textIconColor,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}