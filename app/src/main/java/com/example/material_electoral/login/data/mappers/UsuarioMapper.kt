package com.example.material_electoral.login.data.mappers


import com.example.material_electoral.login.data.remote.service.response.UsuarioResponse
import com.example.material_electoral.login.domain.model.UsuarioModel


fun UsuarioResponse.toDomain() = UsuarioModel(
    usuario.id,
    usuario.email,
    usuario.password,
    usuario.nombre,
    usuario.rol,
    usuario.token
)
