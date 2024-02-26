package io.lostpacket.breadit.ui.screens.home

import io.lostpacket.breadit.app.models.Listing
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface HomeEndpoint {

    @GET("r/all/hot")
    suspend fun getHot() : Listing
}