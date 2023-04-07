package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antisnusbolaget.slutasnusa2.navigation.BooleanPair

@Composable
fun BottomNav(
    navController: NavController,
    bottomBarState: MutableState<BooleanPair>,
) {
    when (bottomBarState.value) {
        BooleanPair(shouldShowNav = true, shouldShowYellow = false) ->
            Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                Text(text = "navBar")
            }
        BooleanPair(shouldShowNav = false, shouldShowYellow = true) ->
            Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                Text(text = "yellow")
            }
    }
}
