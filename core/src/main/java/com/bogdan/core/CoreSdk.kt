package com.bogdan.core

import com.bogdan.core.data.model.UserInfo
import io.reactivex.Single

interface CoreSdk {

    /**
     * Retrieves userInfoDetails to be consumed inside host application.
     */
    fun getUserInfoDetails(): Single<UserInfo>

    companion object {
        private var myTestInstance: CoreSdk? = null

        fun getInstance(): CoreSdk {
            return if (myTestInstance != null) ({
                myTestInstance = CoreSdkImpl.getInstance()
                myTestInstance
            }) as CoreSdkImpl else {
                CoreSdkImpl.getInstance()
            }
        }

        /**
         * Used for setting a mock instance for unit tests
         */
        internal fun setInstance(sdk: CoreSdk) {
            myTestInstance = sdk
        }
    }
}