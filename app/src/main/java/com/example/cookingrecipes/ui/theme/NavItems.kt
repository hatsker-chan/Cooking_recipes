package com.example.cookingrecipes.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cookingrecipes.R

sealed class NavItems(
    val titleResId: Int,
    val imageVector: ImageVector
){
    object List : NavItems(
        titleResId = R.string.list_nav_label,
        imageVector = Icons.Outlined.Search
    )

    object Favourite : NavItems(
        titleResId = R.string.fav_nav_label,
        imageVector = Icons.Outlined.Favorite
    )
}

