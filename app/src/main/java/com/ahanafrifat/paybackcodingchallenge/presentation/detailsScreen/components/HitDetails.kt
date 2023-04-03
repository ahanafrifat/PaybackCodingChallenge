package com.ahanafrifat.paybackcodingchallenge.presentation.detailsScreen.components

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.presentation.common.InfoBox
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.*
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews
import com.ahanafrifat.paybackcodingchallenge.utils.demoHit

@Composable
fun HitDetails(
    hit: Hit,
    onItemClick: (Hit) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
                    .padding(all = MEDIUM_PADDING),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    InfoBox(
                        icon = R.drawable.outline_recommend_24,
                        iconColor = MaterialTheme.colors.primary,
                        text = hit.likes.toString(),
                        textColor = MaterialTheme.colors.topAppBarContentColor
                    )

                    InfoBox(
                        icon = R.drawable.baseline_download_24,
                        iconColor = MaterialTheme.colors.primary,
                        text = hit.downloads.toString(),
                        textColor = MaterialTheme.colors.topAppBarContentColor
                    )

                    InfoBox(
                        icon = R.drawable.baseline_comment_24,
                        iconColor = MaterialTheme.colors.primary,
                        text = hit.comments.toString(),
                        textColor = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            }

        }
    }
}

@Composable
@DevicePreviews
fun HitDetailsPreview() {
    HitDetails(hit = demoHit(), onItemClick = {})
}