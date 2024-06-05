package com.areeb.boxoffice.data.remote.util.calladapter

import com.areeb.boxoffice.data.remote.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.io.EOFException
import java.io.IOException
import java.lang.reflect.Type

class FlowCallAdapter(
    private val responseType: Type
) : CallAdapter<Type, Flow<ApiResponse<Type>>> {
    override fun responseType() = responseType

    override fun adapt(call: Call<Type>): Flow<ApiResponse<Type>> = flow {
        val response = call.awaitResponse()
        emit(ApiResponse.create(response))
    }.catch { error ->
        if (error.message?.contains("Failed to connect to") == true || error is java.io.EOFException)
            emit(ApiResponse.create(EOFException("Unable to connect to the internet , please try again later")))
        else if(error is com.google.gson.JsonSyntaxException)
            emit(ApiResponse.create(IOException("Sorry, there appears to be a system\nproblem, please try again later")))
        else
            emit(ApiResponse.create(error))

    }
}