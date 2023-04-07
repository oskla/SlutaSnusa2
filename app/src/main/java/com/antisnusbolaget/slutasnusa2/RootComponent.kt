package com.antisnusbolaget.slutasnusa2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.NavGraph

@Composable
fun RootComponent() {
    val navController = rememberNavController()
    val materialBlue700 = Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            scaffoldState = scaffoldState,
            content = { scaffoldPadding ->
                Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
                    NavGraph(navController = navController)
                }
            },
            bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } },
        )
    }
}
