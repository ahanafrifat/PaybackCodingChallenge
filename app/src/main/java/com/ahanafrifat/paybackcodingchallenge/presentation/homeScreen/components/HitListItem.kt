package com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.HITS_ITEM_HEIGHT
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.LARGE_PADDING
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.MEDIUM_PADDING
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.topAppBarContentColor
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews
import com.ahanafrifat.paybackcodingchallenge.utils.demoHit

@Composable
fun HitListItem(
    hit: Hit,
    onItemClick: (Hit) -> Unit
) {

    Box(
        modifier = Modifier
            .height(HITS_ITEM_HEIGHT)
            .clickable { onItemClick(hit) },
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(), model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(hit.previewURL)
                    .placeholder(drawableResId = R.drawable.ic_placeholder)
                    .error(drawableResId = R.drawable.ic_placeholder)
                    .build(),
                contentDescription = stringResource(R.string.hits_thumbnail)
            )
        }


        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {

                Text(
                    text = hit.user,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = hit.tags,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

@Composable
@DevicePreviews
fun HitListItemPreview() {
    HitListItem(hit = demoHit(), onItemClick = {})
}

