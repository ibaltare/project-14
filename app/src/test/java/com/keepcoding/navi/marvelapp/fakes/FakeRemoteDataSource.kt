package com.keepcoding.navi.marvelapp.fakes

import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.data.remote.response.CharacterDTO
import com.keepcoding.navi.marvelapp.data.remote.response.ComicDTO
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.utils.FakeData
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeRemoteDataSource(private val success: Boolean): RemoteDataSource {

    private val sharedFlow = MutableSharedFlow<List<Comic>>()

    suspend fun emit(list: List<Comic>){
        sharedFlow.emit(list)
    }

    override suspend fun getCharacters(offset: Int, limit: Int): Result<List<CharacterDTO>> {
        if (success)
            return Result.success(FakeData.getCharacterDTOList())
        else
            return Result.failure(Throwable("Get Character Fail"))
    }

    override suspend fun getComics(
        characterId: Int,
        offset: Int,
        limit: Int
    ): Result<List<ComicDTO>> {
        if (success)
            return Result.success(FakeData.getComicDTOList())
        else
            return Result.failure(Throwable("Get Comic Fail"))
    }
}