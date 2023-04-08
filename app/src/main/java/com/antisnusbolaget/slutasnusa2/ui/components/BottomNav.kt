package com.antisnusbolaget.slutasnusa2.ui.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.helper.BackPressHandler
import com.antisnusbolaget.slutasnusa2.navigation.BooleanPair

@Composable
fun BottomNav(
    navController: NavController,
    bottomBarState: MutableState<BooleanPair>,
    onClickNext: () -> Unit,
    onClickBack: () -> Unit,
) {
    when (bottomBarState.value) {
        BooleanPair(shouldShowNav = true, shouldShowYellow = false) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Text(text = "navBar")
            }
        BooleanPair(shouldShowNav = false, shouldShowYellow = true) ->
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

@Composable
fun BottomScaffoldYellow(
    onClickNext: () -> Unit,
    onClickBack: () -> Unit,
    navController: NavController,
) {
    BackPressHandler(
        onBackPressed = {
            navController.popBackStack()
            onClickBack()
        },
    )

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.height(45.dp).zIndex(1f),
            painter = painterResource(id = R.drawable.logo_no_undertext),
            contentDescription = "logo",
            alpha = 0.3f,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier)

            IconButton(onClick = onClickNext) {
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "next",
                )
            }
        }
    }
}

private const val componentName = "BottomBar"

@SuppressLint("UnrememberedMutableState")
@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    Column() {
        BottomNav(
            bottomBarState = mutableStateOf(BooleanPair(shouldShowYellow = true, shouldShowNav = false)),
            navController = rememberNavController(),
            onClickNext = {},
            onClickBack = {},
        )
        BottomNav(
            bottomBarState = mutableStateOf(
                BooleanPair(
                    shouldShowYellow = false,
                    shouldShowNav = true,
                ),
            ),
            navController = rememberNavController(),
            onClickNext = {},
            onClickBack = {},
        )
    }
}
