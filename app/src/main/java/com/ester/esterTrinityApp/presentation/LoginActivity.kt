package com.ester.esterTrinityApp.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ester.esterTrinityApp.R
import com.ester.esterTrinityApp.data.model.User
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var sharedPref: SharedPreferences
    var isRemembered = false
    private val loginViewModel: LoginScreenViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBarLogin.visibility = View.GONE

        setupViewModelObservers()

        sharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        if (isRemembered) {
            startActivity(Intent(this@LoginActivity, MainHomeListActivity::class.java))
        }

        cadastroLoginButton.setOnClickListener {
            val cadastroIntent = Intent(this, RegisterActivity::class.java)
            startActivity(cadastroIntent)
        }

        materialButtonEntrar.setOnClickListener {
            if (validateFieldsIsEmptyOrValid(campoLoginSenha.text.toString())) {
                progressBarLogin.visibility = View.VISIBLE
                user = User(
                    email = campoLoginEmail.text.toString(),
                    password = campoLoginSenha.text.toString()
                )
                loginViewModel.startLogin(user, progressBarLogin)
            }
        }
    }

    private fun validateFormatPasswordRegex(password: String): Boolean {
        val pattern: Pattern
        val passwordPattern =
            "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}\$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }


    private fun validateFieldsIsEmptyOrValid(password: String): Boolean {

        if (campoLoginSenha.text!!.isEmpty()) {
            campoLoginSenha.error = "Insira senha"
            campoLoginSenha.requestFocus()
            return false
        }
        if (!validateFormatPasswordRegex(password)) {
            campoLoginSenha.error =
                "A senha deve ter entre 8 a 16 digitos e ter pelo menos uma letra\n" +
                        "maiuscula, uma minuscula, um número e um caractér especial."
            campoLoginSenha.requestFocus()
            return false
        }
        if (campoLoginEmail.text!!.isEmpty()) {
            campoLoginEmail.error = "Insira um e-mail"
            campoLoginEmail.requestFocus()
            return false
        }
        if (campoLoginSenha.text!!.isEmpty() && campoLoginEmail.text!!.isEmpty()) {
            Toast.makeText(this, "Preencha todos campos", Toast.LENGTH_LONG).show()
        }

        return true
    }


    private fun setupViewModelObservers() {
        loginViewModel.errorLogin.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        loginViewModel.userLoginStart.observe(this, Observer {
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString("USER_EMAIL", user.email)
            editor.apply()
            val homeIntent = Intent(this, MainHomeListActivity::class.java).apply {
                putExtra("USER_EMAIL", user.email)
            }
            startActivity(homeIntent)
            campoLoginSenha.text?.clear()
        })

    }


}