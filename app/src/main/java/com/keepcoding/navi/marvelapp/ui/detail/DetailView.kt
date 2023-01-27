package com.keepcoding.navi.marvelapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ScreenDetail(heroId: Int = -1, onBackClick: ()->(Unit) = {}){
    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = { onBackClick() }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "go back")
        }
    }
}