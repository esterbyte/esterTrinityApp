package com.ester.esterTrinityApp.util

import com.google.firebase.auth.FirebaseAuth

class FirebaseConfiguration {
    private lateinit var referenciaAuth: FirebaseAuth

    fun getFirebaseAuth(): FirebaseAuth {
        referenciaAuth = FirebaseAuth.getInstance()
        return referenciaAuth
    }
}