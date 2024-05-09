package com.example.material_electoral.login.domain.util

sealed class Resource<out T>{
    //class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val mensaje: String?) : Resource<T>()
}

