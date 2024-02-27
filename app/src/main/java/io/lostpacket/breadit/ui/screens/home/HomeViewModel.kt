package io.lostpacket.breadit.ui.screens.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lostpacket.breadit.app.logging.debug
import io.lostpacket.breadit.app.models.Listing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val handle: SavedStateHandle,
    val repo: HomeRepo
) : ViewModel() {


    private val _homeStateFlow = MutableStateFlow<List<PostSummaryStateHolder>>(emptyList())
    val homeFlow: StateFlow<List<PostSummaryStateHolder>> get() = _homeStateFlow

    fun load() {

        if (_homeStateFlow.value.isEmpty()) {
            viewModelScope.launch {
                debug("loading from repo")
                val listing = repo.get(
                    HomeRepo.GetHomeSpec()
                )
                debug("home response listing = $listing")
                _homeStateFlow.value = listing.toStateHolder()
            }
        }
    }


    private fun Listing.toStateHolder(): List<PostSummaryStateHolder> {
        return this.data?.children?.map {
            PostSummaryStateHolder(
                title = it.data?.title ?: "",
                thumbnail = it.data?.thumbnail ?: "",
                votes = (it.data?.score ?: 0).toString(),
            )
        } ?: emptyList()
    }

    @Immutable
    data class PostSummaryStateHolder(
        val title: String,
        val thumbnail: String,
        val votes: String
    )
}