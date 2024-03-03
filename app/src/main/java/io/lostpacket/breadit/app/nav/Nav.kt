package io.lostpacket.breadit.app.nav

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import io.lostpacket.breadit.R
import io.lostpacket.breadit.ui.screens.home.HomeFragment
import io.lostpacket.breadit.ui.screens.login.LoginFragment
import javax.inject.Inject

@ActivityScoped
class Nav @Inject constructor(@ActivityContext private val activity: Context) {


     fun navigate(to: Screen) {

        val klazz = when (to) {
            Screen.Home -> HomeFragment::class.java
            Screen.Login -> LoginFragment::class.java
        }

         (activity as FragmentActivity).supportFragmentManager.commit {
            replace(R.id.fragment_container, klazz, bundleOf())
            setReorderingAllowed(true)
            addToBackStack("name") // Name can be null
        }

    }

    sealed interface Screen {
        data object Home: Screen
        data object Login: Screen
    }
}


