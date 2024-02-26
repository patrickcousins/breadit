package io.lostpacket.breadit.app.network

import dagger.Lazy
import io.lostpacket.breadit.app.config.Config
import io.lostpacket.breadit.app.scopes.user.UserRepo
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(private val userRepo: Lazy<UserRepo>) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val credentials = Credentials.basic(Config.clientId(), "")


        //json baby
        builder.addHeader("Accept", "application/json")

        if (userRepo.get().hasToken()) {
            val token = userRepo.get().readAccessToken()
            builder.addHeader("Authorization", "bearer $token")
        } else {
            // basic auth
            builder.addHeader("Authorization", credentials)
        }

        return chain.proceed(builder.build())
    }

}