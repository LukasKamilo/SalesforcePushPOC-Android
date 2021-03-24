package com.lucascamilo.salesforcepushpoc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.salesforce.android.connectedapp.ConnectedAppConfiguration
import com.salesforce.android.connectedapp.SalesforceConnectedApp
import com.salesforce.android.service.common.utilities.control.Async
import com.salesforce.androidsdk.accounts.UserAccount
import com.salesforce.androidsdk.app.SalesforceSDKManager
import com.salesforce.androidsdk.rest.ClientManager
import com.salesforce.androidsdk.rest.RestClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SalesforceSDKManager.initNative(this, null, MainActivity::class.java)

        login()
    }

    /**
     * Initiates user login process.
     */
    private fun login() {
        SalesforceSDKManager.getInstance().clientManager.getRestClient(this) {
            run {
                // left blank intentionally
                val connectedApp: SalesforceConnectedApp = SalesforceConnectedApp.create(
                    this, ConnectedAppConfiguration.Builder()
                        .salesforceInstanceURL("fwtech-dev-ed.my.salesforce.com")
                        .authTokenProvider(getAuthTokenProvider())
                        .build()
                )

                // Add the push notification listener
                connectedApp.addPushNotificationListener(MyPushNotificationListener())

                // Register for push notifications
                connectedApp.registerDeviceForPushNotifications().onError { operation, throwable ->
                    // TO DO: Handle error
                    Log.d("MainActivity2", "Operation $operation - Throwable $throwable")
                }
            }
        }
    }

    /**
     * Returns an MobileSDKAuthTokenProvider for the provided UserAccount.
     */
    private fun getAuthTokenProvider(): MobileSdkAuthTokenProvider {
        val user =
            SalesforceSDKManager.getInstance().userAccountManager.currentUser

        val accMgrAuthTokenProvider = ClientManager.AccMgrAuthTokenProvider(
            SalesforceSDKManager.getInstance().clientManager,
            user.instanceServer,
            user.authToken,
            user.refreshToken)

        return MobileSdkAuthTokenProvider(accMgrAuthTokenProvider, user.authToken)
    }
}