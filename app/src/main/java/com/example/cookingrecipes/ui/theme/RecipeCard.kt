package com.example.cookingrecipes.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingrecipes.domain.Recipe


@Composable
fun RecipeCard(
    recipe: Recipe
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = recipe.name,
            modifier = Modifier
                .padding(4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            bitmap = recipe.imageBitmap,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            contentScale = ContentScale.FillWidth
        )
//        Column {
//            recipe.ingredients.forEach {
//                val mes = it.amount
//                Text(
//                    text = "${it.name}: ${it.measure}",
//                    modifier = Modifier.padding(4.dp)
//                )
//            }
//        }
    }
}