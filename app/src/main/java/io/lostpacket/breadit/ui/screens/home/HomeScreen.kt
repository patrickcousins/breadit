package io.lostpacket.breadit.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {

    LaunchedEffect(true) {
        homeViewModel.load()
    }
    Text(
        text = "home screen...loading",
    )
}