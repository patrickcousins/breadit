package io.lostpacket.breadit.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lostpacket.breadit.app.logging.debug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val handle: SavedStateHandle,
    val repo: HomeRepo
) : ViewModel() {


    private val _homeStateFlow = MutableStateFlow<List<String>>(listOf())
    val homeFlow: StateFlow<List<String>> get() = _homeStateFlow

    fun load() {

        if (_homeStateFlow.value.isEmpty()) {
            viewModelScope.launch {
                debug("loading from repo")
                val list = repo.get(
                    HomeRepo.GetHomeSpec()
                )
                debug("home response string = $list")
                //_homeStateFlow.value = list
            }
        }
    }
}