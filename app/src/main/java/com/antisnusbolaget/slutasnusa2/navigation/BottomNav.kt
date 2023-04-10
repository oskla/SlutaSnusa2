package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antisnusbolaget.slutasnusa2.ui.components.BottomScaffoldYellow

@Composable
fun BottomNav(
    navController: NavController,
    bottomBarState: BottomBarVisibility,
    onClickNext: () -> Unit,
    onClickBack: () -> Unit,
) {
    when (bottomBarState) {
        BottomBarVisibility(shouldShowNav = true, shouldShowYellow = false) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Text(text = "navBar")
            }
        BottomBarVisibility(shouldShowNav = false, shouldShowYellow = true) ->
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .height(60.dp),
            ) {
                BottomScaffoldYellow(
                    onClickNext = onClickNext,
                    onClickBack = onClickBack,
                    navController = navController,
                )
            }
    }
}
