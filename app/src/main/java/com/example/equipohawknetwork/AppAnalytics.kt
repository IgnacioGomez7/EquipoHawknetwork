package com.example.equipohawknetwork

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AppAnalytics {
    private fun fa(ctx: Context) = FirebaseAnalytics.getInstance(ctx)

    fun signUp(ctx: Context)           = fa(ctx).logEvent(FirebaseAnalytics.Event.SIGN_UP, Bundle())
    fun verificationSent(ctx: Context) = fa(ctx).logEvent("email_verification_sent", Bundle())
    fun verified(ctx: Context)         = fa(ctx).logEvent("email_verified", Bundle())
    fun login(ctx: Context)            = fa(ctx).logEvent(FirebaseAnalytics.Event.LOGIN, Bundle())
}

