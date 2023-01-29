package com.keepcoding.navi.marvelapp.ui.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val heros = viewModel.heros.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Marvel Heroes")},
                actions = {
                    var isMenuOpen by remember { mutableStateOf(false) }
                    IconButton(onClick = { isMenuOpen = true }) {
                        Icon(imageVector = Icons.Filled.MoreVert , contentDescription = "Menu")
                        MenuHome(isExpanded = isMenuOpen, onItemClick = { viewModel.cleanAndReload() }) {
                            isMenuOpen = false
                        }
                    }
                }
            ) },
        content = { ListHero( heros.value, showDetail){id -> viewModel.setFavorite(id)}}
    )
}

@Composable
fun ListHero(heroes: List<Hero>, showDetail: (Int) -> Unit, setFavorite: (Int) -> Unit){
    LazyColumn {
        items(heroes.size){
            ItemHero( heroes[it],  showDetail, setFavorite)
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHero(hero: Hero, onItemclicked: (Int) -> Unit, setFavorite: (Int) -> Unit){
    val context = LocalContext.current
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
            OutlinedButton(onClick = {
                setFavorite(hero.id)
                Toast.makeText(context,
                    if(!hero.favorite) {"Agregado a favoritos"} else {"Eliminado de favoritos"},
                    Toast.LENGTH_SHORT).show() },
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_like_fill),
                    contentDescription = "Like",
                    tint = if (hero.favorite) Like else Dislike ,
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    )
}

@Composable
fun MenuHome(isExpanded: Boolean, onItemClick: ()->Unit, onDismiss: ()->Unit){
    DropdownMenu(expanded = isExpanded, onDismissRequest = onDismiss) {
        DropdownMenuItem(onClick = {
            onItemClick()
            onDismiss()
        }) {
            Text(text = "Reiniciar datos")
        }
    }
}
