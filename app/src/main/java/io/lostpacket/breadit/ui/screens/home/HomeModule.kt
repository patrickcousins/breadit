package io.lostpacket.breadit.ui.screens.home

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.lostpacket.breadit.app.network.SignedInRetrofit
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideEndpoint(retrofit: SignedInRetrofit): HomeEndpoint {
        return retrofit.client.create<HomeEndpoint>()
    }
}