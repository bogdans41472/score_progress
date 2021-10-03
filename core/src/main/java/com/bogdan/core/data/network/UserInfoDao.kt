package com.bogdan.core.data.network

import com.bogdan.core.data.model.UserInfo
import com.bogdan.core.data.network.service.UserInfoService
import com.bogdan.core.exception.InternalException
import com.bogdan.core.exception.enum.InternalError
import com.bogdan.core.util.DefaultRxSchedulers
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException

class UserInfoDao {
    private var rxSchedulers: DefaultRxSchedulers = DefaultRxSchedulers()

    fun getUserInfo(): Single<UserInfo> {
        return getRetrofit().create(UserInfoService::class.java).getUserInfo()
            .observeOn(rxSchedulers.ui())
            .subscribeOn(rxSchedulers.io())
    }

    // Ideally the initialization of retrofit and OkHttp should be done via a factory method
    // in order to have a common place of initialization all DAOs rather than just this one.
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    companion object {
        private const val base_url = "https://android-interview.s3.eu-west-2.amazonaws.com/"
    }
}