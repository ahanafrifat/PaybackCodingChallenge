package com.ahanafrifat.paybackcodingchallenge.navigation

import com.ahanafrifat.paybackcodingchallenge.utils.Constants
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.DETAILS_HIT_ID
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.DETAIL_SCREEN
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.HOME_SCREEN

sealed class Screen(val route: String) {

    object HomeScreen : Screen(HOME_SCREEN)

    object DetailScreen : Screen("$DETAIL_SCREEN/{hitId}") {

        fun passId(hitId: Int): String {
            return "$DETAIL_SCREEN/$hitId"
        }
    }

}
