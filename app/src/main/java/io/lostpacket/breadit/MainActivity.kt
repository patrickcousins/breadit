package io.lostpacket.breadit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController
import io.lostpacket.breadit.app.logging.debug
import io.lostpacket.breadit.app.nav.Destinations
import io.lostpacket.breadit.ui.screens.home.HomeScreen
import io.lostpacket.breadit.ui.screens.login.LoginScreen
import io.lostpacket.breadit.ui.screens.login.LoginState
import io.lostpacket.breadit.ui.screens.login.LoginViewModel
import io.lostpacket.breadit.ui.theme.BreaditTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLoggedInInitially = loginViewModel.loggedInStateFlow.value == LoginState.In
        checkIntentForLoginValues()
        setContent {
            BreaditTheme {

                val navController = rememberNavController(
                    startDestination = if (isLoggedInInitially) {
                        Destinations.Home
                    } else {
                        Destinations.Login
                    }
                )
                NavBackHandler(navController)

                NavHost(navController) { destination ->

                    when (destination) {
                        is Destinations.Home -> HomeScreen(
                            homeViewModel = hiltViewModel()
                        )

                        is Destinations.Login -> LoginScreen(
                            loginViewModel = loginViewModel,
                            onLoginSuccess = { navController.navigate(Destinations.Home) }
                        )
                    }
                }
            }
        }
    }

    private fun checkIntentForLoginValues() {
        val intentDataString = intent.data.toString()
        debug(intentDataString)

        if (intentDataString.startsWith("breadit://")) {
            loginViewModel.exchangeToken(intentDataString)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BreaditTheme {
//        Box(modifier = Modifier
//            .fillMaxSize(1.0f) // it will fill parent box
//            .padding(8.dp),
//            contentAlignment = Alignment.Center) {
//
//            LoginScreen(loginViewModel)
//        }
//    }
//}