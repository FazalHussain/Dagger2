package com.example.advancedagger2.network.auth

import com.example.advancedagger2.models.Users
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for defining API endpoints
 */
interface AuthApi {

    /**
     * [Flowable] is used because it is easy to convert into live data
     */
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Flowable<Users>
}