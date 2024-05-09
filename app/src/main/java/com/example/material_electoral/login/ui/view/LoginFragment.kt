package com.example.material_electoral.login.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.material_electoral.MainActivity
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.databinding.FragmentLoginBinding
import com.example.material_electoral.login.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding.loginBtn.setOnClickListener {
            Log.e("Email",binding.emailInput.text.toString())
            Log.e("Password",binding.passwordInput.text.toString())

            viewModel.login(binding.emailInput.text.toString(), binding.passwordInput.text.toString())
            setupObservers()
        }
    }

    private fun setupObservers(){

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.fetchUsuarios.observe(viewLifecycleOwner, Observer { result ->
            if(result != null){
                lifecycleScope.launch(Dispatchers.IO) {
                    saveDataUser(result)
                }
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })

    }

    private suspend fun saveDataUser(usuario: UsuarioModel?) {
        Log.e("usuario savedatauser", usuario.toString())
        context?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey(("id_usuario"))] = usuario!!.id
            preferences[stringPreferencesKey(("email_usuario"))] = usuario.email
            preferences[stringPreferencesKey(("password_usuario"))] = usuario.password
            preferences[stringPreferencesKey("nombre_usuario")] = usuario.nombre
            preferences[stringPreferencesKey(("rol_usuario"))] = usuario.rol
            preferences[stringPreferencesKey(("token_usuario"))] = usuario.token
        }
    }


}