package io.lostpacket.breadit.app.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignedOutRetrofit @Inject constructor(
    val client: Retrofit
)