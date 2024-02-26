package io.lostpacket.breadit.app.scopes.user

import dagger.Lazy
import io.lostpacket.breadit.app.config.Config
import io.lostpacket.breadit.app.logging.debug
import io.lostpacket.breadit.ui.screens.login.LoginState
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(
    private val tokenEndpoint: Lazy<TokenEndpoint>,
) {

    private var _token : Token? = null


    fun readAccessToken() : String? {
        debug("read token: ${_token?.accessToken}")
        return _token?.accessToken
    }

    fun hasToken() : Boolean {
        return _token?.accessToken != null
    }

    suspend fun exchangeToken(code: String): LoginState {

        try {
            //create the raw post string
            val postBodyString = "grant_type=authorization_code&code=$code&redirect_uri=${Config.redirectUri()}"
            val body = RequestBody.create(MediaType.parse("text/plain"), postBodyString)
            debug(postBodyString)
            _token = tokenEndpoint.get().exchangeToken(
                code = code
            )
            debug(_token.toString())
        } catch (ex :Exception) {
            debug(ex.toString())
        }
        return if (_token?.accessToken != null) LoginState.In else LoginState.Error("uhoh")
    }
}