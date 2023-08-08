package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.homescreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.ui.components.TopBar
import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.homescreen.HomeScreenViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.homescreen.HomeState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (uiState.value.loadingState) {
            LoadingState.FAILED -> {
                LaunchedEffect(uiState.value) {
                    navController.navigate(Screen.OnBoarding.route)
                }
            }
            LoadingState.LOADING -> {
                CircularProgressIndicator()
            }
            LoadingState.SUCCESS -> {
                HomeScreenContent(uiState = uiState)
            }
        }
    }
}

@Composable
fun HomeScreenContent(uiState: State<HomeState>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ss_logo),
            contentDescription = "logo",
            modifier = Modifier.size(136.dp),
        )

        Box(
            modifier = Modifier.height(90.dp),
        ) {
            TopBar()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            HomeScreenInfo(
                daysSinceQuit = uiState.value.userData.costPerUnit.toString(),
                moneySaved = "3460",
            ) // TODO calculation for money saved
        }
    }
}

@Composable
private fun HomeScreenInfo(daysSinceQuit: String, moneySaved: String) {
    val columnWidthInt = remember { mutableStateOf(0) }
    val columnWidthDp =
        with(LocalDensity.current) { columnWidthInt.value.toDp() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(horizontal = 16.dp)
            .onGloballyPositioned {
                columnWidthInt.value = it.size.width
            },
    ) {
        TextBold(
            modifier = Modifier,
            text = daysSinceQuit,
            fontSize = 92.sp,
        )

        TextBold(
            modifier = Modifier.padding(bottom = 36.dp),
            text = "dagar snusfri",
            fontSize = 32.sp,
        )

        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.line),
            contentDescription = "divider",
            modifier = Modifier
                .width(columnWidthDp) // To make the line as wide as the column
                .height(8.dp),
        )

        TextBold(
            modifier = Modifier.padding(top = 12.dp),
            text = moneySaved,
            fontSize = 92.sp,
        )

        TextBold(
            modifier = Modifier,
            text = "kr sparat",
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(36.dp)) // To make up for the extra padding added by bold font
    }
}

private const val componentName = "Home Screen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    HomeScreen(navController = rememberNavController())
}
