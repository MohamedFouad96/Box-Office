package com.areeb.boxoffice.data.cache

import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.RemoteKeys


class FakeRemoteKeyDao : RemoteKeysDao {


     val remoteKeysList = mutableListOf<RemoteKeys>()

    override suspend fun insertAll(remoteKey: List<RemoteKeys>) {
        remoteKeysList.addAll(remoteKey)
    }

    override suspend fun insertKey(remoteKey: RemoteKeys) {
        remoteKeysList.removeIf { remoteKey.id == it.id }
        remoteKeysList.add(remoteKey)
    }

    override suspend fun remoteKeysId(id: String, type: String): RemoteKeys? {
        return remoteKeysList.find { it.id == id && it.type == type }
    }

    override suspend fun clearRemoteKey(id: String) {
        remoteKeysList.removeAll { it.id == id }
    }

    override suspend fun clearRemoteKeyByType(type: String) {
        remoteKeysList.removeAll { it.type == type }

    }

    override suspend fun clearAllRemoteKeys() {
        remoteKeysList.clear()
    }

    override suspend fun clearRemoteKeysWithout(id: String) {
        remoteKeysList.retainAll { it.id == id }
    }

    override suspend fun getKeys(): List<RemoteKeys> {
        return remoteKeysList.toList()
    }
}