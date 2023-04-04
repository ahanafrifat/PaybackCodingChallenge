package com.ahanafrifat.paybackcodingchallenge.data.remote

import com.ahanafrifat.paybackcodingchallenge.domain.model.ApiResponse
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit

class FakePaybackApi : PaybackApi {

    override suspend fun getHits(
        apiKey: String?,
        query: String?,
        page: Int?,
        perPage: Int?
    ): ApiResponse {
        return ApiResponse(hits = hits)
    }

    private val hits = listOf(
        Hit(
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
        ),
        Hit(
            id = 7889170,
            pageURL = "https://pixabay.com/photos/squirrel-rodent-wildlife-mammal-7889170/",
            type = "photo",
            tags = "squirrel, rodent, wildlife",
            previewURL = "https://cdn.pixabay.com/photo/2023/03/31/04/15/squirrel-7889170_150.jpg",
            previewWidth = 150,
            previewHeight = 99,
            webformatURL = "https://pixabay.com/get/g3f067d81ce6b8c01589cc77022a75ca0e90f936523a250068971f1b899c6cdf3ffbf50ccffddda269f698381bcb758fde40d626228a50c4990887c599d482c31_640.jpg",
            webformatWidth = 640,
            webformatHeight = 421,
            largeImageURL = "https://pixabay.com/get/gb416550def420097be22c08794ecd02e5dcca9bf96349a106f1a39a1e589e74b3d5dd6d18b1e1d5683aec1524c89daba6b726dfe3c7fef856ea37fcd5d13ec33_1280.jpg",
            imageWidth = 5748,
            imageHeight = 3783,
            imageSize = 3066678,
            views = 1508,
            downloads = 1395,
            collections = 5,
            likes = 52,
            comments = 29,
            userId = 1767157,
            user = "Ralphs_Fotos",
            userImageURL = "https://cdn.pixabay.com/user/2023/04/03/02-11-23-81_250x250.jpg"
        )
    )
}
