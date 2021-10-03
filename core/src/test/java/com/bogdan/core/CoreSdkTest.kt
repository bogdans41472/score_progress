package com.bogdan.core

import com.bogdan.core.data.model.PassStatus
import com.bogdan.core.data.model.PersonaType
import com.bogdan.core.data.model.UserInfo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.io.IOException


class CoreSdkTest {

    private lateinit var sut: CoreSdk

    @Before
    fun setUp() {
        val mockSdk = mock(CoreSdkImpl::class.java)
        CoreSdk.setInstance(mockSdk)
        sut = CoreSdk.getInstance()
    }

    @Test
    fun getUserInfo_THEN_returnUserInfo() {
        val userInfo = UserInfo(PassStatus.PASS, null, PassStatus.PASS, PersonaType.EXPERIENCED, null, null)
        whenever(sut.getUserInfoDetails()).thenReturn(Single.just(userInfo))
        sut.getUserInfoDetails().test()
            .assertValue { info ->
                assert(info.accountIDVStatus == PassStatus.PASS)
                assert(info.dashboardStatus == PassStatus.PASS)
                assert(info.personaType == PersonaType.EXPERIENCED)
                info.augmentedCreditScore == null
            }
    }

    @Test
    fun getUserInfo_WHEN_no_internet_THEN_return_error() {
        whenever(sut.getUserInfoDetails()).thenReturn(Single.error(IOException()))
        sut.getUserInfoDetails().test()
            .assertError(IOException::class.java)
    }
}