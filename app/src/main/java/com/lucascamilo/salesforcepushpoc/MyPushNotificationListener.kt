package com.lucascamilo.salesforcepushpoc

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.salesforce.android.cases.ui.CaseUIClient
import com.salesforce.android.connectedapp.PushNotificationListener

class MyPushNotificationListener : PushNotificationListener {

    private val TAG = "MyPushListener"

    override fun onPushNotificationReceived (message: RemoteMessage?) {

        // Extract the Case ID from the data. The name of the key depends
        // on how you bundled the freeform data in your Apex trigger...
        val caseId = message?.getData()?.get("caseid")

        // TO DO: Extract any other info from the data
        Log.d(TAG, message.toString())

        var uiClient: CaseUIClient? = null

        // TO DO: Get the UI client from configuration

        if (uiClient != null) {
            // Pass case update information to the UI client.
            uiClient.notifyCaseUpdated(caseId)
        }
    }
}