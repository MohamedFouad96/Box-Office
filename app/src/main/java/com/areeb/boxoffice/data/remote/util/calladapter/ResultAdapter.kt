package com.areeb.boxoffice.data.remote.util.calladapter

import com.areeb.boxoffice.data.remote.util.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<ApiResponse<Type>>> {
    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> = ResultCall(call)
    override fun responseType(): Type = type
}