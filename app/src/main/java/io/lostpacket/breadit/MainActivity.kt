package io.lostpacket.breadit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import io.lostpacket.breadit.app.nav.Nav
import io.lostpacket.breadit.ui.screens.login.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var nav: Nav


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        nav.navigate(Nav.Screen.Login)
    }


}

//
//@Composable
//fun BreaditMain(isLoggedInInitially: Boolean = false,
//                loginViewModel: LoginViewModel) {
//    BreaditTheme {
//
//        val navController = rememberNavController(
//            startDestination = if (isLoggedInInitially) {
//                Destinations.Home
//            } else {
//                Destinations.Login
//            }
//        )
//        NavBackHandler(navController)
//
//        NavHost(navController) { destination ->
//
//            when (destination) {
//                is Destinations.Home -> HomeScreen(
//                    homeViewModel = hiltViewModel()
//                )
//
//                is Destinations.Login -> LoginScreen(
//                    loginViewModel = loginViewModel,
//                    onLoginSuccess = { navController.navigate(Destinations.Home) }
//                )
//            }
//        }
//    }
//}