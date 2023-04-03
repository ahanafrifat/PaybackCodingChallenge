package com.ahanafrifat.paybackcodingchallenge.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ahanafrifat.paybackcodingchallenge.utils.DevicePreviews
import com.ahanafrifat.paybackcodingchallenge.R
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.INFO_ICON_SIZE
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.SMALL_PADDING
import com.ahanafrifat.paybackcodingchallenge.presentation.ui.theme.titleColor

@Composable
fun InfoBox(
    icon: Int,
    iconColor: Color,
    text: String,
    textColor: Color
) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )
        Text(
            modifier = Modifier.alpha(ContentAlpha.medium),
            text = text,
            color = textColor,
            fontSize = MaterialTheme.typography.overline.fontSize
        )
    }
}

@Composable
@DevicePreviews
fun InfoBoxPreview() {
    InfoBox(
        icon = R.drawable.outline_recommend_24,
        iconColor = MaterialTheme.colors.primary,
        text = "test",
        textColor = MaterialTheme.colors.titleColor
    )
}