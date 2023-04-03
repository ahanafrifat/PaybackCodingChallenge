package com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahanafrifat.paybackcodingchallenge.navigation.Screen
import com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen.components.SearchTopBar
import com.ahanafrifat.paybackcodingchallenge.presentation.common.ListContent

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val TAG = "HomeScreenLog"
    val searchQuery by viewModel.searchQuery
    val allHits = viewModel.searchHits.collectAsLazyPagingItems()
    val context = LocalContext.current
    var hitId by remember {
        mutableStateOf(0)
    }
    var user by remember {
        mutableStateOf("")
    }
    var isDialogShown by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        SearchTopBar(text = searchQuery,
            onTextChange = {
                viewModel.updateQuery(query = it)
            },
            onSearchClicked = {
                Log.d(TAG, it)
                viewModel.searchHits(query = it)
            },
            onCloseClicked = {

            }
        )
    },
        content = {
            Box(modifier = Modifier.padding(it)) {
                ListContent(
                    hits = allHits,
                    onItemClick = { hit ->
                        hitId = hit.id
                        user = hit.user
                        isDialogShown = true
                    })
            }
        }
    )

    if (isDialogShown) {
        AlertDialog(
            title = {
                Text(text = "Hi from ${user}")
            },
            text = {
                Text(text = "Do you want to see the details?")
            },
            onDismissRequest = { isDialogShown = false },
            confirmButton = {
                TextButton(onClick = {
                    isDialogShown = false
                    navController.navigate(Screen.DetailScreen.passId(hitId = hitId))
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { isDialogShown = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

