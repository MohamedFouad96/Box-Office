package com.areeb.boxoffice.data.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.RemoteKeys
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RemoteKeysDaoTest: KoinTest {

    private val dao: RemoteKeysDao by inject()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown()= runTest {
        dao.clearAllRemoteKeys()
    }


    @Test()
    fun insertKeyWithSingleKeyThenShouldReturnThisKey() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)

        dao.insertKey(remoteKey = key)


        val retrievedPhoto = dao.getKeys().firstOrNull()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto?.id, `is`(key.id))

    }


    @Test()
    fun insertAllWithMultipleThenShouldReturnThisKeys() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))


        val retrievedPhoto = dao.getKeys()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto.size, `is`(2))

    }

    @Test()
    fun remoteKeysIdWithMultipleThenShouldReturnTheSelectedRemoteKey() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))


        val retrievedPhoto = dao.remoteKeysId("1")

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto?.nextKey, `is`(2))

    }


    @Test()
    fun remoteKeysIdWithIncorrectIdThenShouldReturnNull() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))


        val retrievedPhoto = dao.remoteKeysId("0")

        assertThat(retrievedPhoto, nullValue())

    }

    @Test()
    fun getKeysWithInsertedKeysThenShouldReturnAllKeys() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))


        val retrievedPhoto = dao.getKeys()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto.size, `is`(2))
        assertThat(retrievedPhoto.first().nextKey, `is`(2))
    }


    @Test()
    fun clearRemoteKeyWithInsertedKeysThenShouldTheKeyRemoved() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))

        dao.clearRemoteKey("1")
        val retrievedPhoto = dao.getKeys()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto.size, `is`(1))
        assertThat(retrievedPhoto.first().nextKey, `is`(3))
    }

    @Test()
    fun clearAllRemoteKeysWithInsertedKeysThenShouldGettingEmptyList() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)

        dao.insertAll(remoteKey = listOf(key,key1))

        dao.clearAllRemoteKeys()
        val retrievedPhoto = dao.getKeys()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto.isEmpty(), `is`(true))
    }


    @Test()
    fun clearRemoteKeysWithoutWithInsertedKeysThenShouldGettingOneKey() = runTest {
        val key = RemoteKeys( id = "1", nextKey = 2)
        val key1 = RemoteKeys( id = "2", nextKey = 3)
        val key2 = RemoteKeys( id = "3", nextKey = 4)

        dao.insertAll(remoteKey = listOf(key,key1 ,key2))

        dao.clearRemoteKeysWithout("3")
        val retrievedPhoto = dao.getKeys()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto.size, `is`(1))
        assertThat(retrievedPhoto.first().nextKey, `is`(4))
    }

}