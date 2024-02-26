package io.lostpacket.breadit.ui.screens.login

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.lostpacket.breadit.app.config.Config

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
) {

    val loggedIn = loginViewModel.loggedInStateFlow.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .fillMaxSize(1.0f) // it will fill parent box
        .padding(8.dp),
        contentAlignment = Alignment.Center) {

        when(loggedIn.value) {
            is LoginState.Out -> Out(loginViewModel)
            is LoginState.Error -> Error()
            is LoginState.In -> In(onLoginSuccess)
            is LoginState.LoggingIn -> LoggingIn()
        }
    }
}

@Composable
fun Out(loginViewModel: LoginViewModel) {
    val context = LocalContext.current
    Button(onClick = {
        loginViewModel.startingOauthBrowserFlow()
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Config.loginUri())
            )
        )
    }) {
        Text(text = "Click to sign in via reddit")
    }
}



@Composable
fun LoggingIn() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
    )
}


@Composable
fun In(onLoginSuccess: () -> Unit) {
    Text(text = "You're in!")
    onLoginSuccess()
}




@Composable
fun Error() {
    Text(text = "ohno")
}