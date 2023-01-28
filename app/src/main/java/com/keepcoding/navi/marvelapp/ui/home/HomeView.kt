package com.keepcoding.navi.marvelapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.keepcoding.navi.marvelapp.R
import com.keepcoding.navi.marvelapp.domain.Hero
import com.keepcoding.navi.marvelapp.ui.theme.Dislike
import com.keepcoding.navi.marvelapp.ui.theme.Like

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ScreenHome(viewModel: HomeViewModel = hiltViewModel(),showDetail: (Int)->(Unit) = {}){
    val scaffoldState = rememberScaffoldState()
    //val scope = rememberCoroutineScope()
    val heros = viewModel.heros.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text(text = "Marvel Heroes")}) },
        content = { ListHero( heros.value, showDetail )}
    )
}

@Composable
fun ListHero(heroes: List<Hero>, showDetail: (Int) -> Unit){
    LazyColumn {
        items(heroes.size){
            ItemHero(hero = heroes[it], onItemclicked = showDetail)
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHero(hero: Hero, onItemclicked: (Int) -> Unit){
    /**
     * Componente para crear listas verticales de texto o imagenes
     * es un template con funcionamiento basico para crear una lista simple
     */
    ListItem(
        modifier = Modifier.clickable(onClick = { onItemclicked(hero.id) }),
        text = { Text(text = hero.name, style = MaterialTheme.typography.h6) },
        icon = {Image(
            painter = rememberAsyncImagePainter(model = hero.thumbnail),
            contentDescription = hero.name,
            modifier = Modifier
                .size(80.dp, 80.dp)
                .clip(RoundedCornerShape(16.dp)),
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Crop
        )},
        trailing = {
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_like_fill),
                    contentDescription = "Like",
                    tint = if (hero.favorite) Like else Dislike ,
                    modifier = Modifier.size(32.dp)
                )
            }

        }
    )
}
