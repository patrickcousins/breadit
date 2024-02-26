package io.lostpacket.breadit.ui.screens.home

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface HomeEndpoint {

    @GET("r/all/hot")
    suspend fun getHot() : Response<ResponseBody>
}