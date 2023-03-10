package com.keepcoding.navi.marvelapp.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.ui.theme.BackgroundColorText
import com.keepcoding.navi.marvelapp.ui.theme.DetailCard

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ScreenDetail(heroId: Int = -1,viewModel: DetailViewModel = hiltViewModel(), onBackClick: ()->(Unit) = {}){
    //val viewModel: DetailViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val hero = viewModel.hero.collectAsState()
    val comics = viewModel.comics.collectAsState()
    viewModel.getHero(heroId)
    viewModel.getComics(heroId)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Hero Detail")},
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "Back")
                    }
                }
            ) },
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeroDetail(hero.value)
                SectionDivider(title = "Comics")
                ComicsView(comics.value)
            }
        }
    )
}

@Composable
fun HeroDetail(hero: CharacterEntity){
    Card(elevation = 2.dp,
        backgroundColor = DetailCard) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            Image(
                painter = rememberAsyncImagePainter(model = hero.thumbnail),
                contentDescription = hero.name,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = hero.name,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = hero.description,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SectionDivider(title: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Divider(Modifier.fillMaxWidth())
    Text(text = title,
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp)
    Divider(Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ComicsView(comics: List<Comic>){
    LazyRow {
        items(comics.size){
            ItemComic(comic = comics[it])
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun ItemComic(comic: Comic){
    Card(modifier = Modifier
        .height(260.dp)
        .width(220.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = comic.thumbnail),
            contentDescription = comic.name,
            contentScale = ContentScale.Crop
        )
        Box(contentAlignment = Alignment.BottomCenter){
            Text(text = comic.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundColorText))
        }
    }
}