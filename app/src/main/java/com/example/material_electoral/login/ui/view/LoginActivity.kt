package com.example.material_electoral.login.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.material_electoral.MainActivity
import com.example.material_electoral.databinding.ActivityLoginBinding
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "dataStore")
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        this.supportActionBar!!.hide()
        setupObservers()
        binding.loginBtn.setOnClickListener {
            if(binding.emailInput.text.isEmpty()){
                Toast.makeText(this, "Ingresa un email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.passwordInput.text.isEmpty()){
                Toast.makeText(this, "Ingresa un password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(loginViewModel.isValidEmail(binding.emailInput.text.toString())){
                Toast.makeText(this, "Ingresa un email correcto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.e("Email",binding.emailInput.text.toString())
            Log.e("Password",binding.passwordInput.text.toString())

            loginViewModel.login(binding.emailInput.text.toString(), binding.passwordInput.text.toString())
        }

    }

    private fun setupObservers(){

        loginViewModel.isLoading.observe(this, Observer {
            if (it){
                binding.loader.visibility = View.VISIBLE
            }else{
                binding.loader.visibility = View.GONE
            }
        })

        loginViewModel.fetchUsuarios.observe(this, Observer { result ->
            if(result != null){
                lifecycleScope.launch(Dispatchers.IO) {
                    saveDataUser(result)
                }
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        })

        loginViewModel.error.observe(this, Observer { error ->

            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

        })

    }

    private suspend fun saveDataUser(usuario: UsuarioModel?) {
        Log.e("usuario savedatauser", usuario.toString())
        this.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(("id_usuario"))] = usuario!!.id
            preferences[stringPreferencesKey(("email_usuario"))] = usuario.email
            preferences[stringPreferencesKey(("password_usuario"))] = usuario.password
            preferences[stringPreferencesKey("nombre_usuario")] = usuario.nombre
            preferences[stringPreferencesKey(("rol_usuario"))] = usuario.rol
            preferences[stringPreferencesKey(("token_usuario"))] = usuario.token
        }
    }

}