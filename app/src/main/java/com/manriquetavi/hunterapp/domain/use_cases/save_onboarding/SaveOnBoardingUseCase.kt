package com.manriquetavi.hunterapp.domain.use_cases.save_onboarding

import com.manriquetavi.hunterapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}