package com.ahanafrifat.paybackcodingchallenge.presentation.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.topAppBarBackgroundColor
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.topAppBarContentColor
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews
import com.ahanafrifat.paybackcodingchallenge.utils.demoHit

@Composable
fun DefaultTopBar(
    title: String,
    onBackClicked: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = title,
            color = MaterialTheme.colors.topAppBarContentColor
        )
    },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_icon)
                )
            }
        }
    )
}

@Composable
@DevicePreviews
fun TopBarPreview() {
    DefaultTopBar (title = demoHit().user, onBackClicked={})
}