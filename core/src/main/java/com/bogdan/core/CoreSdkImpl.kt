package com.bogdan.core

import android.content.Context
import com.bogdan.core.data.model.UserInfo
import com.bogdan.core.data.network.UserInfoDao
import com.bogdan.core.exception.InternalException
import com.bogdan.core.exception.enum.InternalError
import io.reactivex.Single
import java.io.IOException
import java.net.UnknownHostException


open class CoreSdkImpl: CoreSdk {

     lateinit var internalContext: Context

    override fun getUserInfoDetails(): Single<UserInfo> {
        val userInfoDao = UserInfoDao()
        return userInfoDao.getUserInfo()
            .onErrorResumeNext {cause -> handlerError(cause)}
    }

    private fun handlerError(throwable: Throwable?): Single<UserInfo> {
        if (throwable is IOException || throwable is UnknownHostException) {
            return Single.error(
                InternalException(
                    InternalError.NETWORK_ERROR,
                    "There was a problem with your connection, please check your connection"
                )
            )
        }
        return Single.error(throwable)
    }

    fun initializeWithContext(context: Context) {
        internalContext = context
    }

    companion object {
        fun getInstance(): CoreSdkImpl {
            return Holder.INSTANCE
        }

        private object Holder {
            val INSTANCE by lazy { CoreSdkImpl() }
        }
    }
}