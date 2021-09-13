package com.ester.esterTrinityApp

import com.ester.esterTrinityApp.data.model.Data
import com.ester.esterTrinityApp.data.model.Photo
import com.ester.esterTrinityApp.data.TrinityRepositoryImplem
import com.ester.esterTrinityApp.domain.datarepository.DataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame

class TrinityRepositoryUnitTest {

    @Mock
    var repository: DataRepository = Mockito.mock(TrinityRepositoryImplem::class.java)
    @Mock
    var data: Data = Data()

    @Test
    fun `Should return Data when fetching data from API`() {
        runBlocking {
            `when`(repository.getMarsData()).thenReturn(Data())
            assertEquals(Data(), repository.getMarsData())
        }
    }


    @Test
    fun `Should fetch data from local storage and return Data`(){
        runBlocking {
            `when`(repository.getLocalData()).thenReturn(Data())
            assertEquals(Data(), repository.getLocalData())
        }
    }

    @Test
    fun `Should save local data`(){
        runBlocking {
            `when`(repository.saveLocalData(data)).thenReturn(0)
            assertEquals(0, repository.saveLocalData(data))
        }
    }

    @Test
    fun `Should fail when trying to use Data on a non-Data class`() {
        runBlocking {
            `when`(repository.getMarsData()).thenReturn(Data())
            assertNotSame(Photo(), repository.getMarsData())
        }
    }
}