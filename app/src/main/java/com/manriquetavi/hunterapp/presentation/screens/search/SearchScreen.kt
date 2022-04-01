package com.manriquetavi.hunterapp.presentation.screens.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.manriquetavi.hunterapp.presentation.common.ListContent
import com.manriquetavi.hunterapp.ui.theme.statusBarColor

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val hunters = searchViewModel.searchedHunters.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(query = it) },
                onSearchClicked = { searchViewModel.searchHunters(query = it) },
                onClosedClicked = { navController.popBackStack() }
            )
        },
        content = {
            ListContent(
                hunters = hunters,
                navController = navController
            )
        }
    )
}