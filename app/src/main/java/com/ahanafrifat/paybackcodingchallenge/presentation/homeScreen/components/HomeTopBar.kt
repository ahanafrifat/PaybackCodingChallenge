package com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.topAppBarBackgroundColor
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.topAppBarContentColor
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews

@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.topAppBarContentColor
        )
    },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search))
            }
        }
    )
}

@Composable
@DevicePreviews
fun HomeTopBarPreview() {
    HomeTopBar {}
}