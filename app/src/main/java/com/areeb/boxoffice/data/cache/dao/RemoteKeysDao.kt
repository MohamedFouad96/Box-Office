package com.areeb.boxoffice.data.cache.dao

import androidx.room.*
import com.areeb.boxoffice.data.cache.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: RemoteKeys)

    @Query("SELECT * FROM remote_keys WHERE id = :id AND type = :type")
    suspend fun remoteKeysId(id: String,type: String = "default"): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE id = :id")
    suspend fun clearRemoteKey(id: String)

    @Query("DELETE FROM remote_keys WHERE type = :type")
    suspend fun clearRemoteKeyByType(type: String)

    @Query("DELETE FROM remote_keys ")
    suspend fun clearAllRemoteKeys()

    @Query("DELETE FROM remote_keys WHERE id != :id")
    suspend fun clearRemoteKeysWithout(id: String)

    @Query("SELECT * FROM remote_keys")
    suspend fun getKeys():List<RemoteKeys>



}