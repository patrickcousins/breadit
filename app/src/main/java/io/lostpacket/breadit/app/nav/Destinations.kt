package io.lostpacket.breadit.app.nav

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Destinations : Parcelable {
    @Parcelize
    data object Home : Destinations()

    @Parcelize
    data object Login : Destinations()
}