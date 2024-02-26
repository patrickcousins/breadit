package io.lostpacket.breadit.app.scopes.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * {
 *     "access_token": Your access token,
 *     "token_type": "bearer",
 *     "expires_in": Unix Epoch Seconds,
 *     "scope": A scope string,
 *     "refresh_token": Your refresh token
 * }
 */
@JsonClass(generateAdapter = true)
data class Token(
    @Json(name = "access_token") val accessToken: String?,
    @Json(name = "token_type") val tokenType: String?,  // should always be "bearer"
    @Json(name = "expires_in") val expiresInSeconds: Long?,
    @Json(name = "refresh_token") val refreshToken: String?,
)
