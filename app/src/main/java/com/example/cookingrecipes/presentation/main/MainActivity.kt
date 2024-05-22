package com.example.cookingrecipes.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.cookingrecipes.presentation.recipeList.RecipeListViewModel
import com.example.cookingrecipes.ui.theme.CookingRecipesTheme

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CookingRecipesTheme(darkTheme = false, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }

    override fun onDestroy() {
        viewModel.removeRecipes()
        super.onDestroy()
    }
}