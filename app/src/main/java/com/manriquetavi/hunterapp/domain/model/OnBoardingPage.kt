package com.manriquetavi.hunterapp.domain.model

import androidx.annotation.DrawableRes
import com.manriquetavi.hunterapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.welcome,
        title = "Greetings",
        description = "Are you HxH fan? Because if you are then we have a great news for you!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.search,
        title = "Explore",
        description = "Find your favorite hunter and learn some of the things that you didn't know about."
    )
    object Third: OnBoardingPage(
        image = R.drawable.powerful,
        title = "Power",
        description = "Check out your hunter's power and see how much are they strong comparing to others"
    )
}
