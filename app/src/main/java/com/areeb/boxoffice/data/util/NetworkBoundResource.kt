package com.areeb.boxoffice.data.util



import com.areeb.boxoffice.data.remote.util.ApiEmptyResponse
import com.areeb.boxoffice.data.remote.util.ApiErrorResponse
import com.areeb.boxoffice.data.remote.util.ApiResponse
import com.areeb.boxoffice.data.remote.util.ApiSuccessResponse
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<DB, REMOTE>() {

    private val TAG = "NetworkBoundResources"

    fun getResult() = flow<Resource<DB>> {

        emit(Resource.loading(null))
        val localData = fetchFromLocal()
        emit(Resource.loading(data = localData))

        if (shouldFetchFromRemote(localData)) {
            emit(Resource.loading(null))
            fetchFromRemote().collect { apiResponse ->
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                           if (processRemoteResponse(apiResponse))   {
                               apiResponse.body?.let { saveRemoteData(it) }
                               emit(fetchFromLocal().let { dbData ->
                                   Resource.success(dbData)
                               })
                           } else {
                               emit(Resource.success(null))
                           }
                    }
                    is ApiErrorResponse -> {
                        emit(
                            Resource.error(
                                apiResponse.errorMessage,
                                null
                            )
                        )
                    }
                    is ApiEmptyResponse -> {
                        emit(Resource.success(null))
                    }

                    else -> {}
                }
            }
        } else {
            emit(fetchFromLocal().let { Resource.success(it) })
        }
    }

    protected abstract suspend fun fetchFromLocal(): DB?
    protected abstract suspend fun fetchFromRemote(): Flow<ApiResponse<REMOTE>>
    protected abstract suspend fun processRemoteResponse(
        response: ApiSuccessResponse<REMOTE>
    ): Boolean


    protected abstract suspend fun shouldFetchFromRemote(
        local: DB?,
    ): Boolean

    protected abstract suspend fun saveRemoteData(remote: REMOTE): Unit

}