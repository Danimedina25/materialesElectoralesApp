package com.example.material_electoral.login.domain.model

data class UsuarioModel (
    val id:String = "",
    val email:String = "",
    val password:String = "",
    val nombre:String = "",
    val rol:String = "",
    val token:String = ""
)