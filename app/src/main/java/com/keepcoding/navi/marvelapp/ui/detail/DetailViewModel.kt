package com.keepcoding.navi.marvelapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.domain.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository): ViewModel() {

    private val _comics = MutableStateFlow(emptyList<Comic>())
    val comics: StateFlow<List<Comic>> get() = _comics

    private val _hero = MutableStateFlow(CharacterEntity(0,"","","",false))
    val hero: StateFlow<CharacterEntity> get() = _hero

    fun getHero(id: Int){
        viewModelScope.launch {
            _hero.value = withContext(Dispatchers.IO){
                repository.getCharacter(id)
            }
        }
    }

    fun getComics(id: Int){
        viewModelScope.launch {
            repository
                .getComics(id)
                .flowOn(Dispatchers.IO)
                .collect {
                    _comics.value = it
                }
        }
    }
}