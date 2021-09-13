package com.ester.esterTrinityApp.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ester.esterTrinityApp.data.model.User
import com.ester.esterTrinityApp.presentation.BaseViewModel
import com.ester.esterTrinityApp.util.FirebaseConfiguration
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterViewModel : BaseViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth
    var errorCadastro = MutableLiveData<String>()
    var startCadastro = MutableLiveData<Unit>()

    fun registerUser(user: User, progressBarCadastro: View) {
        progressBarCadastro.visibility = View.VISIBLE
        firebaseAuth = FirebaseConfiguration().getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBarCadastro.visibility = View.GONE
                startCadastro.value = Unit
            } else {
                progressBarCadastro.visibility = View.GONE
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthWeakPasswordException) {
                    errorCadastro.value = "A senha deve conter de 8 a 16 digitos e conter pelo menos uma letra\n" +
                            "maiscula, uma minuscula, um número e um caractér especial."
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    errorCadastro.value = "Digite um e-mail válido"
                } catch (e: FirebaseAuthUserCollisionException) {
                    errorCadastro.value = "Essa conta já existe"
                }catch (e: FirebaseNetworkException){
                    errorCadastro.value = "Erro de rede, verifique a conexão com a internet"
                } catch (e: Exception) {
                    errorCadastro.value = "ao cadastrar usuário, foi encontrado o erro: " + e.message + " contate o admnistrador"
                    e.printStackTrace()
                }
            }
        }
    }
}