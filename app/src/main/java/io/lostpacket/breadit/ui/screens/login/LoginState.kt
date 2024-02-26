package io.lostpacket.breadit.ui.screens.login

sealed class LoginState {
    object In : LoginState()
    object Out : LoginState()
    object LoggingIn : LoginState()
    data class Error(val message: String) : LoginState()
}
