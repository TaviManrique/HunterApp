package com.manriquetavi.hunterapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.manriquetavi.hunterapp.R
import com.manriquetavi.hunterapp.domain.model.Hunter
import com.manriquetavi.hunterapp.navigation.Screen
import com.manriquetavi.hunterapp.presentation.components.RatingWidget
import com.manriquetavi.hunterapp.presentation.components.ShimmerEffect
import com.manriquetavi.hunterapp.ui.theme.*
import com.manriquetavi.hunterapp.util.Constants.BASE_URL

@Composable
fun ListContent(
    hunters: LazyPagingItems<Hunter>,
    navController: NavHostController,
    spaceBetween: Dp = SMALL_PADDING
) {
    val result = handlePagingResult(hunters = hunters)
    if(result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            items(
                items = hunters,
                key = { hunter ->
                    hunter.id
                }
            ) { hunter ->
                hunter?.let {
                    HunterItem(hunter = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    hunters: LazyPagingItems<Hunter>
): Boolean {
    hunters.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, hunters = hunters)
                false
            }
            hunters.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@Composable
fun HunterItem(
    hunter: Hunter,
    navController: NavHostController
) {
    val painter = rememberImagePainter(data = "$BASE_URL${hunter.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .height(HUNTER_ITEM_HEIGHT)
            .clickable { navController.navigate(Screen.Details.passHunterId(hunterId = hunter.id)) },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(LARGE_PADDING)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hunter_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomStart = LARGE_PADDING, bottomEnd = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hunter.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hunter.about,
                    color = MaterialTheme.colors.topAppBarContentColor.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hunter.rating
                    )
                    Text(
                        text = "(${hunter.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HunterItemPreview() {
    HunterItem(
        hunter = Hunter(
            id = 1,
            name = "Gon Freecss",
            image = "",
            about = "Gon Freecss (ゴン゠フリークス, Gon Furīkusu) is a Rookie Hunter and the son of Ging Freecss. Finding his father is Gon's motivation in becoming a Hunter.\n" +
                "He has been the main protagonist for most of the series, having said role in the Hunter Exam, Zoldyck Family, Heavens Arena, Greed Island, and Chimera Ant arcs. He was also the deuteragonist of the Yorknew City arc.",
            rating = 5.0,
            power = 90,
            month = "May",
            day = "5",
            family = listOf(
                "Ging Freecss (Father)",
                "Mito Freecss (Uncle)",
                "Abe (Great-Grandmother)",
                "Don Freecss (Ancestor)"
            ),
            abilities = listOf(
                "Jajanken"
            ),
            nenTypes = listOf(
                "Enhancement",
                "Transmutation",
                "Emission"
            )
        ),
        navController = rememberNavController()
    )
}