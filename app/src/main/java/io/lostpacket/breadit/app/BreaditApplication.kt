package io.lostpacket.breadit.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BreaditApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }


    companion object {
        var appContext: Context? = null
    }
}
