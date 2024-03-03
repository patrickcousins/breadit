package io.lostpacket.breadit.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.lostpacket.breadit.app.logging.debug
import io.lostpacket.breadit.app.nav.Nav
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    @Inject lateinit var nav: Nav

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val isLoggedInInitially = loginViewModel.loggedInStateFlow.value == LoginState.In
        checkIntentForLoginValues()

        return ComposeView(requireContext()).apply {
            setContent {
                LoginScreen(
                    loginViewModel = loginViewModel,
                    onLoginSuccess = { nav.navigate(Nav.Screen.Home) }
                )
            }
        }
    }


    private fun checkIntentForLoginValues() {
        val intentDataString = activity?.intent?.data.toString()
        debug(intentDataString)

        if (intentDataString.startsWith("breadit://")) {
            activity?.intent = null
            loginViewModel.exchangeToken(intentDataString)
        }
    }
}