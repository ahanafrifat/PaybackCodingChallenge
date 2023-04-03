package com.ahanafrifat.paybackcodingchallenge.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.DarkGray
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.LightGray
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.SMALL_PADDING
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews
import java.net.ConnectException
import java.net.SocketTimeoutException

@ExperimentalMaterialApi
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    hits: LazyPagingItems<Hit>? = null
) {

    var message by remember {
        mutableStateOf("Find what you lookiing for!")
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        hits = hits,
        error = error
    )

}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unreachable"
        }
        is ConnectException -> {
            "Internet Unreachable"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    error: LoadState.Error? = null,
    hits: LazyPagingItems<Hit>? = null
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }

//    PullRefreshIndicator(
//        refreshing = isRefreshing,
//        state = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { hits?.refresh() }))

    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { hits?.refresh() })



    Box (modifier = Modifier.pullRefresh(pullRefreshState)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alpha = alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
        modifier = Modifier.align(Alignment.TopCenter))

    }
}

@ExperimentalMaterialApi
@Composable
@DevicePreviews
fun EmptyScreenPreview(){
    EmptyScreen()
}
