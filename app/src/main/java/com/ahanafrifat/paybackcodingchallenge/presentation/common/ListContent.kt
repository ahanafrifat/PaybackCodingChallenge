package com.ahanafrifat.paybackcodingchallenge.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen.components.HitListItem
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.SMALL_PADDING

@ExperimentalMaterialApi
@Composable
fun ListContent(
    hits: LazyPagingItems<Hit>,
    onItemClick: (Hit) -> Unit
) {

    val result = handlePagingResult(hits)
    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = hits,
                key = { hit ->
                    hit.id
                }

            ) { hit ->
                hit?.let {
                    HitListItem(hit = it, onItemClick = { onItemClick(it) })
                }
            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun handlePagingResult(hits: LazyPagingItems<Hit>): Boolean {

    hits.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, hits = hits)
                false
            }
            hits.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> {
                true
            }
        }
    }

}
