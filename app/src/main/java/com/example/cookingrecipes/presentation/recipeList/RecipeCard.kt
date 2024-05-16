package com.example.cookingrecipes.presentation.recipeList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cookingrecipes.domain.Recipe


@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClickListener: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onRecipeClickListener()
            }
    ) {
        Text(
            text = recipe.name,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(100.dp, 100.dp)
                .padding(4.dp),
            contentScale = ContentScale.FillWidth
        )
//        Image(
//            bitmap = Picasso.get().load(recipe.imageUrl).get().asImageBitmap(),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp),
//            contentScale = ContentScale.FillWidth
//        )
    }
}