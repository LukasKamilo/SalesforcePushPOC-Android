package com.lucascamilo.salesforcepushpoc

import com.salesforce.android.service.common.http.AuthTokenProvider
import com.salesforce.android.service.common.http.ResponseSummary
import com.salesforce.androidsdk.rest.ClientManager

class MobileSdkAuthTokenProvider(
    private val mTokenProvider: ClientManager.AccMgrAuthTokenProvider,
    private var mAuthToken: String?) : AuthTokenProvider {

    override fun getToken(): String? {
        return mAuthToken
    }

    override fun getTokenType(): String? {
        return "Bearer"
    }

    override fun canRefresh(): Boolean {
        return true
    }

    override fun refreshToken(responseSummary: ResponseSummary) {
        mAuthToken = mTokenProvider.newAuthToken
    }
}