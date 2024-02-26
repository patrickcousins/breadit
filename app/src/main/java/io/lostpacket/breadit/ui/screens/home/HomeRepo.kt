package io.lostpacket.breadit.ui.screens.home

import io.lostpacket.breadit.app.logging.debug
import io.lostpacket.breadit.app.scopes.user.UserRepo
import javax.inject.Inject

class HomeRepo @Inject constructor(
    val endpoint: HomeEndpoint,
    val userRepo: UserRepo,
) {

    suspend fun get(spec: GetHomeSpec) : String {
        val response = endpoint.getHot()
        val code = response.code()
        val body = response.body().toString()

        debug("response code $code")
        return body
    }


    data class GetHomeSpec(
        val before: String = "",
        val after: String = "",
    )
}
