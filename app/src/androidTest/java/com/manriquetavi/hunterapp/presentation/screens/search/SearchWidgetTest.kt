package com.manriquetavi.hunterapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
         composeTestRule.setContent {
             SearchWidget(
                 text = text.value,
                 onTextChange = {
                     text.value = it
                 },
                 onClosedClicked = {},
                 onSearchClicked = {}
             )
         }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Tavi-Manrique")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Tavi-Manrique")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseIconOnce_assertEmptyInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onClosedClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Tavi-Manrique")
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseIconTwice_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onClosedClicked = { searchWidgetShown.value = false },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Tavi-Manrique")
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseIconOnceWhenInputIsEmpty_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onClosedClicked = { searchWidgetShown.value = false },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}