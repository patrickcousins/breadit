package io.lostpacket.breadit.app.logging

import android.util.Log
import androidx.core.os.BuildCompat

fun debug(message: String) {
     //if (BuildConfig.DEBUG) {
          Log.d("brd", message)
     //}
}