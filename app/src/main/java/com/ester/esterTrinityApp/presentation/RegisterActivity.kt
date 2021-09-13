package com.ester.esterTrinityApp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject
import com.ester.esterTrinityApp.R
import com.ester.esterTrinityApp.data.model.User
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {

    private val cadastroViewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupObservers()
        progressBarCadastro.visibility = View.GONE

        materialButtonRegistrar.setOnClickListener {
            if (validateFields(textInputLayoutCadastroSenha.editText?.text.toString())) {
                val user = User(
                    email = textInputLayoutCadastroEmail.editText?.text.toString(),
                    password = textInputLayoutCadastroSenha.editText?.text.toString()
                )
                cadastroViewModel.registerUser(user, progressBarCadastro)
            }
        }
    }


    private fun setupObservers() {
        cadastroViewModel.errorCadastro.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        cadastroViewModel.startCadastro.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })
    }

    private fun validateFields(password: String): Boolean {
        if (editTextCadastroEmail.text!!.isEmpty()) {
            editTextCadastroEmail.error = "Favor inserir e-mail de cadastro"
            editTextCadastroEmail.requestFocus()
            return false
        }
        if (editTextCadastroSenha.text!!.isEmpty()) {
            editTextCadastroSenha.error = "Favor inserir senha de cadastro"
            editTextCadastroSenha.requestFocus()
            return false
        }

        if (!validatePasswordFormat(password)) {
            editTextCadastroSenha.error =
                "A senha deve conter de 8 a 16 digitos e conter pelo menos uma letra\n" +
                        "maiscula, uma minuscula, um número e um caractér especial."
            editTextCadastroSenha.requestFocus()
            return false
        }
        if (editTextCadastroConfirmaSenha.text!!.isEmpty()) {
            editTextCadastroConfirmaSenha.error =
                "Favor inserir confirmação de senha de cadastro"
            editTextCadastroConfirmaSenha.requestFocus()
            return false
        }
        if (editTextCadastroSenha.text.toString() != editTextCadastroConfirmaSenha.text.toString()) {
            editTextCadastroConfirmaSenha.error =
                "A senha e a confirmação de senha precisam ser iguais"
            editTextCadastroConfirmaSenha.requestFocus()
            return false
        }
        return true
    }

    private fun validatePasswordFormat(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val passwordPattern =
            "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}\$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}