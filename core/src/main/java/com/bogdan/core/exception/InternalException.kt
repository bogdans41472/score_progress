package com.bogdan.core.exception

import com.bogdan.core.exception.enum.InternalError
import java.lang.Exception

class InternalException(
    private val errorEnum: InternalError,
    private val developerExternalMessage: String
) : Exception() {

    fun getError(): InternalError {
        return errorEnum
    }

    fun getDeveloperMessage(): String {
        return developerExternalMessage
    }
}