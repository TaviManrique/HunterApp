package com.manriquetavi.hunterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.hunterapp.navigation.SetupNavGraph
import com.manriquetavi.hunterapp.ui.theme.HunterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            HunterAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
