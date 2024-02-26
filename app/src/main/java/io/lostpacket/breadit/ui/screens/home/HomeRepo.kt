package io.lostpacket.breadit.ui.screens.home

import io.lostpacket.breadit.app.models.Listing
import io.lostpacket.breadit.app.scopes.user.UserRepo
import javax.inject.Inject

class HomeRepo @Inject constructor(
    val endpoint: HomeEndpoint,
    val userRepo: UserRepo,
) {

    suspend fun get(spec: GetHomeSpec) : Listing {
        return endpoint.getHot()
    }


    data class GetHomeSpec(
        val before: String = "",
        val after: String = "",
    )
}
