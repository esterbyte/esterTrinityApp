package com.ester.esterTrinityApp.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ester.esterTrinityApp.data.model.User
import com.ester.esterTrinityApp.presentation.BaseViewModel
import com.ester.esterTrinityApp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth


class LoginScreenViewModel : BaseViewModel() {

    var userLoginStart = MutableLiveData<Unit>()
    var errorLogin = MutableLiveData<String>()


    lateinit var firebaseAuthentication: FirebaseAuth

    fun startLogin(user: User, progressBarLogin: View) {



        firebaseAuthentication = FirebaseConfiguration().getFirebaseAuth()
        firebaseAuthentication.signInWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBarLogin.visibility = View.GONE
                userLoginStart.value = Unit
            } else {
                errorLogin.value = "Erro ao realizar login."
                progressBarLogin.visibility = View.GONE
            }
        }
    }
}