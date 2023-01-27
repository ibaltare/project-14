package com.keepcoding.navi.marvelapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.keepcoding.navi.marvelapp.domain.Hero

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ScreenHome(/*viewModel: HomeViewModel = hiltViewModel(),*/showDetail: (Int)->(Unit) = {}){
    val scaffoldState = rememberScaffoldState()
    //val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text(text = "Marvel Heroes")}) },
        content = { ListHero( FakeData.getFakeHeroes(), showDetail )}
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
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.size(16.dp))
        }
    )
    /*
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = { onItemclicked(hero.id) }),
        elevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = hero.thumbnail),
                contentDescription = hero.name,
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop
            )
            Text(
                text = hero.name,
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }*/
}
