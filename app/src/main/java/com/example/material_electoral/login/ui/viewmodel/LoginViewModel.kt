package com.example.material_electoral.login.ui.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.material_electoral.login.data.remote.service.request.UsuarioRequest
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.domain.usecases.LoginUseCase
import com.example.material_electoral.login.domain.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
):ViewModel(){

    private val _fetchUsuarios = MutableLiveData<UsuarioModel?>()
    val fetchUsuarios: LiveData<UsuarioModel?>
        get() = _fetchUsuarios

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun login(email: String, password: String){
        _isLoading.value = true
        val usuarioRequest = UsuarioRequest(email, password)
        viewModelScope.launch {
            when(val result = loginUseCase(usuarioRequest)){
                is Resource.Success -> {
                    Log.d("login result1", Gson().toJson(result))
                    _fetchUsuarios.value = result.data
                }
                is Resource.Error -> {
                    Log.d("login result2", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}