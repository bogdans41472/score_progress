package com.bogdan.core.data.network.service

import com.bogdan.core.data.model.UserInfo
import io.reactivex.Single
import retrofit2.http.GET

interface UserInfoService {
    @GET("endpoint.json")
    fun getUserInfo(): Single<UserInfo>
}