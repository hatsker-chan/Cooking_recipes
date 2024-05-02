package com.example.cookingrecipes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import com.example.cookingrecipes.ui.theme.CookingRecipesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = RecipeRepositoryImpl(
            mapper = RecipeMapper(),
            recipeDao = AppDatabase.getInstance(applicationContext).getUserDao(),
            apiService = ApiFactory.apiService
        )

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch{
            repository.loadData()
            val recipe = repository.getRandomRecipe()
            Log.d("MainActivityTag", recipe.toString())
        }

        setContent {
            CookingRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CookingRecipesTheme {
        Greeting("Android")
    }
}