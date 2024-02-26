package io.lostpacket.breadit.app.scopes.user

import io.lostpacket.breadit.app.config.Config
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenEndpoint {


    @FormUrlEncoded
    @POST("api/v1/access_token")
    suspend fun exchangeToken(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = Config.redirectUri(),
    ): Token

    /*


    @POST("api/v1/access_token")
    suspend fun exchangeToken(
        @Body body: RequestBody
    ): Token
     */
}