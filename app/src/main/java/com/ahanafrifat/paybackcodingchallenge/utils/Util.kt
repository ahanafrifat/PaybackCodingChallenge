package com.ahanafrifat.paybackcodingchallenge.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

fun checkForInternet(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }

}

fun demoHit(): Hit {
    return Hit(
        id = 7885574,
        pageURL = "https://pixabay.com/photos/robin-songbird-feeding-chick-7885574/",
        type = "photo",
        tags = "robin, songbird, feeding",
        previewURL = "https://cdn.pixabay.com/photo/2023/03/29/14/14/robin-7885574_150.jpg",
        previewWidth = 150,
        previewHeight = 100,
        webformatURL = "https://pixabay.com/get/g7399b2443f858512d90c064ebdf8b4eaa7afc00004d19e01ea0e5e91e1de0db82c8c95bf08c5e6da4d2f2c2472cf2a7d7372a5898c23bd476131202c7ee99310_640.jpg",
        webformatWidth = 640,
        webformatHeight = 427,
        largeImageURL = "https://pixabay.com/get/g931e6ae8af5c610f0e30ce41e3f3c8a47861b4ccfc016d77e25589015ce3c68e085c75ed2c5f1ae021d5e6c522d3370755344571698e7ab63ed801baaf23e203_1280.jpg",
        imageWidth = 6000,
        imageHeight = 4000,
        imageSize = 6006470,
        views = 526,
        downloads = 474,
        collections = 4,
        likes = 34,
        comments = 21,
        userId = 10084616,
        user = "Nennieinszweidrei",
        userImageURL = "https://cdn.pixabay.com/user/2022/12/04/11-13-16-116_250x250.png"
    )
}