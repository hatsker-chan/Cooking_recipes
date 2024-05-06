package com.example.cookingrecipes.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingrecipes.domain.Recipe

@Composable
fun RecipeInfo(
    paddingValues: PaddingValues,
    recipe: Recipe
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = recipe.name,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Image(
            recipe.imageBitmap,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ingredients:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            recipe.ingredients.forEach {
                Text(text = "${it.name}: ${it.measure}")
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
        Text(text = "Steps:", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Column {
            recipe.steps.forEach {
                Text(text = "${it.number}) ${it.description}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        val annotatedString = getLinkedText(recipe.sourceUrl)
        ClickableText(
            text = annotatedString,
            style = TextStyle(fontSize = 16.sp, textDecoration = TextDecoration.Underline),
            onClick = {
                annotatedString.getStringAnnotations(it, it)
                    .firstOrNull()?.let { annotatedStr ->
                        uriHandler.openUri(annotatedStr.item)
                    }
            }
        )
    }
}

private fun getLinkedText(string: String): AnnotatedString {
    return buildAnnotatedString {
        pushStringAnnotation(tag = "source_url", annotation = string)
        withStyle(style = SpanStyle(color = LinkColor)) {
            append("See for original recipe")
        }
        pop()
    }
}