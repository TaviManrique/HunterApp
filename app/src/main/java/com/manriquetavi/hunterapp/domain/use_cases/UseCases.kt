package com.manriquetavi.hunterapp.domain.use_cases

import com.manriquetavi.hunterapp.domain.use_cases.get_all_hunters.GetAllHuntersUseCase
import com.manriquetavi.hunterapp.domain.use_cases.get_selected_hunter.GetSelectedHunterUseCase
import com.manriquetavi.hunterapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.manriquetavi.hunterapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.manriquetavi.hunterapp.domain.use_cases.search_hunters.SearchHuntersUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHuntersUseCase: GetAllHuntersUseCase,
    val searchHuntersUseCase: SearchHuntersUseCase,
    val getSelectedHunterUseCase: GetSelectedHunterUseCase
)
