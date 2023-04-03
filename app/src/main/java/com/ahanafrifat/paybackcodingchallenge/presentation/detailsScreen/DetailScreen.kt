package com.ahanafrifat.paybackcodingchallenge.presentation.detailsScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahanafrifat.paybackcodingchallenge.presentation.detailsScreen.components.HitDetails
import com.ahanafrifat.paybackcodingchallenge.presentation.common.DefaultTopBar
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val selectedHit by viewModel.selectedHit.collectAsState()

    Scaffold(topBar = {
        selectedHit?.let { hit ->
            DefaultTopBar(title = hit.user,
                onBackClicked = { navController.popBackStack() })
        }
    }) {
        Surface(modifier = Modifier.padding(it)) {
            selectedHit?.let { selectedHit -> HitDetails(hit = selectedHit, onItemClick = {}) }
        }
    }
}

@Composable
@DevicePreviews
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController())
}