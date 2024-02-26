package io.lostpacket.breadit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.lostpacket.breadit.app.network.AuthInterceptor
import io.lostpacket.breadit.app.network.SignedInRetrofit
import io.lostpacket.breadit.app.network.SignedOutRetrofit
import io.lostpacket.breadit.app.network.UserAgentInterceptor
import io.lostpacket.breadit.app.scopes.user.TokenEndpoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideSignedInRetrofit(okHttpClient: OkHttpClient): SignedInRetrofit {
        return SignedInRetrofit(
            client = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        )
    }

    @Singleton
    @Provides
    fun provideSignedOutRetrofit(okHttpClient: OkHttpClient): SignedOutRetrofit {
        return SignedOutRetrofit(
            client = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            //.eventListenerFactory(PerformanceEventListenerFactory)
            //.authenticator(oAuth2Authenticator)  TODO
            .addInterceptor(authInterceptor)
            .addInterceptor(userAgentInterceptor)
            .retryOnConnectionFailure(true)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }


//    @Singleton
//    @Provides
//    fun provideSignedInOkHttpClient(
//        authInterceptor: AuthInterceptor,
//        userAgentInterceptor: UserAgentInterceptor
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            //.eventListenerFactory(PerformanceEventListenerFactory)
//            //.authenticator(oAuth2Authenticator)  TODO
//            .addInterceptor(authInterceptor)
//            .addInterceptor(userAgentInterceptor)
//            .retryOnConnectionFailure(true)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .build()
//    }

    @Singleton
    @Provides
    fun provideTokenEndpoint(retrofit: SignedOutRetrofit): TokenEndpoint {
        return retrofit.client.create<TokenEndpoint>()
    }
}