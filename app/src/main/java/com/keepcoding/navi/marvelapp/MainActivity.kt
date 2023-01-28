package com.keepcoding.navi.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.keepcoding.navi.marvelapp.ui.Screens
import com.keepcoding.navi.marvelapp.ui.detail.ScreenDetail
import com.keepcoding.navi.marvelapp.ui.home.ScreenHome
import com.keepcoding.navi.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screens.Home.route){

                        composable(Screens.Home.route){
                            ScreenHome(){ heroId ->
                                navController.navigate(Screens.Detail.createRoute(heroId))
                            }
                        }

                        composable(Screens.Detail.route, arguments = listOf(
                            navArgument(Screens.Detail.ARG_HERO_ID){type = NavType.IntType}
                        )){
                            val heroId = it.arguments?.getInt(Screens.Detail.ARG_HERO_ID) ?: -1
                            ScreenDetail(heroId){
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}