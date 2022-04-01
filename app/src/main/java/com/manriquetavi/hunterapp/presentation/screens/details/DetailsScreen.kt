package com.manriquetavi.hunterapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.manriquetavi.hunterapp.util.Constants.BASE_URL
import com.manriquetavi.hunterapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.manriquetavi.hunterapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHunter by detailsViewModel.selectedHunter.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedHunter = selectedHunter,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.GenerateColorPallet -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHunter?.image}",
                        context = context
                    )
                    bitmap?.let {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(bitmap = it)
                        )
                    }
                }
            }
        }
    }
}

