package com.keepcoding.navi.marvelapp.data

import app.cash.turbine.test
import com.keepcoding.navi.marvelapp.data.mappers.EntityMapper
import com.keepcoding.navi.marvelapp.data.mappers.PresentationMapper
import com.keepcoding.navi.marvelapp.data.remote.RemoteDataSource
import com.keepcoding.navi.marvelapp.fakes.FakeLocalDataSource
import com.keepcoding.navi.marvelapp.fakes.FakeRemoteDataSource
import com.keepcoding.navi.marvelapp.utils.FakeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryImpTest {
//SUT
    private lateinit var homeRepositoryImp: HomeRepositoryImp
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var entityMapper: EntityMapper
    private lateinit var presentationMapper: PresentationMapper

    @Before
    fun setUp(){
        presentationMapper = PresentationMapper()
        entityMapper = EntityMapper()
    }

    @Test
    fun `When get characters EXPECT valid data`() = runTest{
        //GIVEN
        remoteDataSource = FakeRemoteDataSource(true)
        localDataSource = FakeLocalDataSource(true)
        homeRepositoryImp = HomeRepositoryImp(
            remoteDataSource,
            localDataSource,
            entityMapper,
            presentationMapper
        )
        // WHEN
        val actual = homeRepositoryImp.getCharacters()

        // THEN
        actual.test {
            localDataSource.emit(FakeData.getFakeCharacters())
            println("----------------------------->>>>>")
            println(awaitItem()[0].name)
            assertEquals(awaitItem()[0].name,"Spiderman")
            awaitComplete()
        }

    }
}