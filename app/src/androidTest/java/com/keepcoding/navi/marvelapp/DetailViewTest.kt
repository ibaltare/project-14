package com.keepcoding.navi.marvelapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.keepcoding.navi.marvelapp.ui.detail.ComicsView
import com.keepcoding.navi.marvelapp.ui.detail.HeroDetail
import com.keepcoding.navi.marvelapp.ui.home.ListHero
import com.keepcoding.navi.marvelapp.utils.FakeData
import org.junit.Rule
import org.junit.Test

class DetailViewTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun whenGetHeroWithFakeDataCreateHeroDetailComponent(){
        // GIVEN
        composeRule.setContent {
            HeroDetail(hero = FakeData.getCharacter())
        }

        Thread.sleep(3000)
        // THEN
        composeRule.onNodeWithText("Spiderman").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Spiderman").assertIsDisplayed()
    }

    @Test
    fun whenGetComicListWithFakeDataCreateListItem(){
        // GIVEN
        composeRule.setContent {
            ComicsView(comics = FakeData.getFakeComics())
        }

        Thread.sleep(3000)
        // THEN
        composeRule.onAllNodesWithText("ComicName").assertCountEquals(1)
    }
}