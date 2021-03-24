package com.lucascamilo.salesforcepushpoc

import com.salesforce.android.service.common.http.AuthenticatedUser
import com.salesforce.androidsdk.accounts.UserAccount

class MobileSdkUser(userAccount: UserAccount) : AuthenticatedUser {

    private val mUserId: String = userAccount.userId

    override fun getUserId(): String {
        return mUserId
    }
}