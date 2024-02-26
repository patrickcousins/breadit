package io.lostpacket.breadit.app.network

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import io.lostpacket.breadit.app.BreaditApplication
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class UserAgentInterceptor @Inject constructor() : Interceptor {

    private val userAgent by lazy {
        " BreaditPackets" + "/" + findVersion() + " Android/1"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("User-agent", userAgent)

        return chain.proceed(builder.build())
    }



    private fun findVersion(): String {
        val context = BreaditApplication.appContext!!

        val manager: PackageManager = context.packageManager
        val info: PackageInfo = manager.getPackageInfo(context.packageName, 0)
        return info.versionName // app version

        // info.packageName
        // info.versionCode
        // info.firstInstallTime
        // info.lastUpdateTime
    }
}