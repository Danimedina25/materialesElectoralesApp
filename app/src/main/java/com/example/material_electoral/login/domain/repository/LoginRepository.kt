package com.example.material_electoral.login.domain.repository

import com.example.material_electoral.login.data.remote.service.request.UsuarioRequest
import com.example.material_electoral.login.data.remote.service.response.UsuarioResponse
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.domain.util.Resource


interface LoginRepository {
    suspend fun getUsuario(usuario: UsuarioRequest?): UsuarioResponse
}

