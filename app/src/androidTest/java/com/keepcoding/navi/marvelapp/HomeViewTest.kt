package com.keepcoding.navi.marvelapp

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.keepcoding.navi.marvelapp.ui.home.ListHero
import com.keepcoding.navi.marvelapp.ui.home.MenuHome
import com.keepcoding.navi.marvelapp.utils.FakeData
import org.junit.Rule
import org.junit.Test

class HomeViewTest {
    @get:Rule
    val composeRule = createComposeRule()
    
    @Test
    fun whenGetHeroListWithFakeDataCreateListItem(){
        // GIVEN
        composeRule.setContent {
            ListHero(heroes = FakeData.getFakeHeroes(), showDetail = { _-> }, setFavorite = { _-> } )
        }

        Thread.sleep(3000)
        // THEN
        composeRule.onAllNodesWithContentDescription("Like").assertCountEquals(4)
    }

}