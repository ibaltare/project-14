package com.keepcoding.navi.marvelapp.data

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.keepcoding.navi.marvelapp.data.local.model.CharacterEntity
import com.keepcoding.navi.marvelapp.data.mappers.EntityMapper
import com.keepcoding.navi.marvelapp.data.mappers.PresentationMapper
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.domain.Comic
import com.keepcoding.navi.marvelapp.fakes.FakeLocalDataSource
import com.keepcoding.navi.marvelapp.fakes.FakeRemoteDataSource
import com.keepcoding.navi.marvelapp.utils.FakeData
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailRepositoryImpTest{
    //SUT
    private lateinit var detailRepositoryImp: DetailRepositoryImp
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var presentationMapper: PresentationMapper

    @Before
    fun setUp(){
        presentationMapper = PresentationMapper()
    }

    @Test
    fun `When get id expects success return CharacterEntity`() = runTest{
        //GIVEN
        remoteDataSource = FakeRemoteDataSource(true)
        localDataSource = FakeLocalDataSource(true)
        detailRepositoryImp = DetailRepositoryImp(remoteDataSource,localDataSource,presentationMapper)

        //WHEN
        val actual = detailRepositoryImp.getCharacter(1)

        //THEN
        Truth.assertThat(actual).isInstanceOf(CharacterEntity::class.java)
    }

    @Test
    fun `When get Comics EXPECT valid data`() = runTest{
        //GIVEN
        remoteDataSource = FakeRemoteDataSource(true)
        localDataSource = FakeLocalDataSource(true)
        detailRepositoryImp = DetailRepositoryImp(remoteDataSource,localDataSource,presentationMapper)
        // WHEN
        val values = mutableListOf<List<Comic>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            detailRepositoryImp.getComics(1).toList(values)
        }

        // THEN
        remoteDataSource.emit(FakeData.getFakeComics())
        assertEquals(values[0][0].name, "Title 0")

        collectJob.cancel()
    }

}