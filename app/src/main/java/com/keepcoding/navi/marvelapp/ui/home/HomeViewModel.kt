package com.keepcoding.navi.marvelapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.navi.marvelapp.domain.Hero
import com.keepcoding.navi.marvelapp.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _heros = MutableStateFlow(emptyList<Hero>())
    val heros: StateFlow<List<Hero>> get() = _heros

    init {
        getHeros()
    }

    private fun getHeros() {
        viewModelScope.launch {
            repository.getCharacters().flowOn(Dispatchers.IO)
                .collect {
                    _heros.value = it
                }
        }
    }

    fun setFavorite(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.setFavorite(id)
            }
        }
    }

    fun cleanAndReload(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.deleteLocalData()
            }
            getHeros()
        }
    }

}