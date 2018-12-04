package com.pixelart.week5daily1.remote

import com.pixelart.week5daily1.model.GitHubUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService{
    @GET("search/users")
    fun getUsers(@Query("q") user: String):Single<GitHubUser>
}