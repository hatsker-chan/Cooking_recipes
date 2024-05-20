package com.example.cookingrecipes.presentation.recipeInfo

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cookingrecipes.R
import com.example.cookingrecipes.domain.Recipe
import com.example.cookingrecipes.ui.theme.LinkColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInfo(
    paddingValues: PaddingValues,
    onBackIconClicked: () -> Unit,
    recipe: Recipe
) {
    val viewModel: RecipeInfoViewModel = viewModel(
        factory = RecipeInfoViewModelFactory(
            application = LocalContext.current.applicationContext as Application,
            recipe = recipe
        )
    )
    val screenState = viewModel.screenState.observeAsState(RecipeInfoScreenState.Initial)
    val uriHandler = LocalUriHandler.current


    when (val currentState = screenState.value) {
        is RecipeInfoScreenState.RecipeInfo -> {
            val curRecipe = currentState.recipe
            Scaffold(
                modifier = Modifier.padding(paddingValues),
                topBar = {
                    TopAppBar(
                        title = {
                            Row {
                                Text(
                                    text = curRecipe.name,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                )
                                IconButton(
                                    onClick = {
                                        viewModel.changeRecipeIsFav(recipe)
                                    },
                                    modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 0.dp)
                                ) {

                                    Icon(
                                        painter = painterResource(
                                            id = if (currentState.isFav)
                                                R.drawable.ic_like_set
                                            else
                                                R.drawable.ic_like
                                        ),
                                        modifier = Modifier.defaultMinSize(30.dp, 30.dp),
                                        contentDescription = null,
                                        tint = if (currentState.isFav) Color.Red else MaterialTheme.colorScheme.onPrimary
                                    )
                                }

                            }

//                            Icon(
//                                modifier = Modifier
//                                    .size(50.dp)
//                                    .padding(8.dp),
//                                imageVector = Icons.Outlined.Favorite,
//                                contentDescription = null
//                            )
                        },

                        navigationIcon = {
                            IconButton(onClick = { onBackIconClicked() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = curRecipe.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp),
                        contentScale = ContentScale.FillWidth
                    )



                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Ingredients:", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        curRecipe.ingredients.forEach {
                            Text(text = "${it.name}: ${it.amount} ${it.measure}")
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                    Text(text = "Steps:", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    Column {
                        curRecipe.steps.forEach {
                            Text(text = "${it.number}) ${it.description}")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    val annotatedString = curRecipe.sourceUrl.getLinkedText()
                    ClickableText(
                        text = annotatedString,
                        style = TextStyle(
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                        onClick = {
                            annotatedString.getStringAnnotations(it, it)
                                .firstOrNull()?.let { annotatedStr ->
                                    uriHandler.openUri(annotatedStr.item)
                                }
                        }
                    )
                }
            }

        }

        RecipeInfoScreenState.Initial -> {}
    }

}

private fun String.getLinkedText(): AnnotatedString {
    return buildAnnotatedString {
        pushStringAnnotation(tag = "source_url", annotation = this@getLinkedText)
        withStyle(style = SpanStyle(color = LinkColor)) {
            append("See for original recipe")
        }
        pop()
    }
}

@Preview
@Composable
fun Prev() {
    val r = Recipe(
        name = "jfkl;dajlfkjas;lfjsakjf;",
        imageUrl = "https://img.spoonacular.com/recipes/659109-312x231.jpg"
    )
    RecipeInfo(paddingValues = PaddingValues(4.dp), onBackIconClicked = { /*TODO*/ }, recipe = r)
}