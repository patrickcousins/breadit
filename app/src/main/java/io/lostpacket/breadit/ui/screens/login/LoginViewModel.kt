package io.lostpacket.breadit.ui.screens.login

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lostpacket.breadit.app.scopes.user.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    val handle: SavedStateHandle,
    private val repo: UserRepo
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<LoginState>(LoginState.Out)
    val loggedInStateFlow: StateFlow<LoginState> get() = _isLoggedIn

    fun exchangeToken(urlString: String) {
        _isLoggedIn.value = LoginState.LoggingIn

        viewModelScope.launch {
            val code = Uri.parse(urlString).getQueryParameter("code")

            code?.let {
                _isLoggedIn.value = repo.exchangeToken(it)
            }
        }
    }

    fun startingOauthBrowserFlow() {
        _isLoggedIn.value = LoginState.LoggingIn
    }
}