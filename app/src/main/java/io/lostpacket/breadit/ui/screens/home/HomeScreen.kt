@file:OptIn(ExperimentalGlideComposeApi::class)

package io.lostpacket.breadit.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import io.lostpacket.breadit.app.models.Children
import io.lostpacket.breadit.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {

    val listing = homeViewModel.homeFlow.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        homeViewModel.load()
    }

    Scaffold {
        HomeList(listing.value?.data?.children ?: emptyList())
    }
}


@Composable
fun HomeList(items: List<Children>) {

    LazyColumn(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        items(items) {
            HomeItem(it)
        }

    }
}

@Composable
fun HomeItem(child: Children) {

    val title = remember {
        child.data?.title ?: ""
    }
    val imgUrl = remember {
        child.data?.thumbnail ?: ""
    }

    Card(
        //modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Row {
            Text(
                text = title,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .weight(1f, fill = true),
                style = Typography.titleMedium
            )

            if (imgUrl.isNotBlank()) {
                GlideImage(
                    model = imgUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                )
            }
        }
    }


}