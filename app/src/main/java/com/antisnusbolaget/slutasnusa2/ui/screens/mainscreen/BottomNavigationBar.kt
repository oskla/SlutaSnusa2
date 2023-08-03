package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.theme.darkYellow

private val BottomNavBarHeight = 64.dp

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Row(
        modifier = modifier
            .height(BottomNavBarHeight),
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                modifier = Modifier.weight(1f),
                item = item,
                selected = selected,
                onItemClick = { onItemClick(item) },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationBarItem(
    modifier: Modifier,
    item: BottomNavItem,
    selected: Boolean,
    onItemClick: (BottomNavItem) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(vertical = 16.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onItemClick(item) },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 45.dp, vertical = 0.dp)
                .fillMaxWidth()
                .background(
                    if (selected) darkYellow else Color.Transparent,
                    shape = RoundedCornerShape(50),
                ),
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.name,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBottomNavigationBar() {
    val bottomNavList = listOf(
        BottomNavItem(
            name = Screen.Home.title,
            route = Screen.Home.route,
            icon = R.drawable.ic_home_foreground,
        ),
        BottomNavItem(
            name = Screen.Achievement.title,
            route = Screen.Achievement.route,
            icon = R.drawable.ic_economy_foreground,
        ),
    )
    MaterialTheme {
        Column() {
            BottomNavigationBar(
                items = bottomNavList,
                navController = rememberNavController(),
                onItemClick = {},
            )
        }
    }
}
