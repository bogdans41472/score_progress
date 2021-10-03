package com.bogdan.score.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogdan.core.CoreSdk
import com.bogdan.core.exception.InternalException
import com.bogdan.core.util.DefaultRxSchedulers


class MainFragmentViewModel : ViewModel() {

    private val sdk = CoreSdk.getInstance()

    val userScore: MutableLiveData<String> = MutableLiveData<String>()
    val maxUserScore: MutableLiveData<String> = MutableLiveData<String>()
    val errorLiveData: MutableLiveData<InternalException> = MutableLiveData<InternalException>()

    init {
        getUserInfoAndPostUpdates()
    }

    private fun getUserInfoAndPostUpdates() {
        with(sdk) {
            getUserInfoDetails()
                .observeOn(DefaultRxSchedulers().ui())
                .subscribe({ userInfo ->
                    maxUserScore.postValue(userInfo.creditReportInfo?.maxScoreValue.toString())
                    userScore.postValue(userInfo.creditReportInfo?.score.toString())
                }, { throwable ->
                    handleError(throwable)
                })
        }
    }

    private fun handleError(it: Throwable) {
        errorLiveData.postValue(it as InternalException)
    }

    fun getUpdatedView() {
        getUserInfoAndPostUpdates()
    }
}