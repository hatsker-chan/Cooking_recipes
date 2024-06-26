package com.example.cookingrecipes.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cookingrecipes.R
import com.example.cookingrecipes.presentation.navigation.Screen

sealed class NavItems(
    val titleResId: Int,
    val imageVector: ImageVector,
    val screen: Screen
) {
    object Recipes : NavItems(
        titleResId = R.string.list_nav_label,
        imageVector = Icons.Outlined.Search,
        screen = Screen.RecipesTab
    )

    object Favourite : NavItems(
        titleResId = R.string.fav_nav_label,
        imageVector = Icons.Outlined.Favorite,
        screen = Screen.FavRecipes
    )
}

