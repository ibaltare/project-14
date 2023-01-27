package com.keepcoding.navi.marvelapp.ui

sealed class Screens(val route: String) {
    object Home: Screens(HOME_BASE_ROUTE)
    object Detail: Screens(DETAIL_BASE_ROUTE){
        const val ARG_HERO_ID = "heroId"
        fun createRoute(heroId: Int): String {
            return "detail/$heroId"
        }
    }
    companion object {
        private const val HOME_BASE_ROUTE = "home"
        private const val DETAIL_BASE_ROUTE = "detail/{heroId}"
    }
}