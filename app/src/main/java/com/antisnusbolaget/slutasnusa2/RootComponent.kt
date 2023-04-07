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

@Composable
fun RootComponent() {
    Box(modifier = Modifier.fillMaxSize()) {
        val materialBlue700 = Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = { Text(text = "drawerContent") },
            content = { scaffoldPadding ->
                Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
                    // TODO NAV GRAPH
                }
            },
            bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } },
        )
    }
}
