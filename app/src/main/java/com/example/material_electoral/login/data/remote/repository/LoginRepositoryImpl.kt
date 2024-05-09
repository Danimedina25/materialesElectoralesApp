package com.example.material_electoral.login.data.remote.repository

import com.example.material_electoral.login.data.mappers.toDomain
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.data.remote.service.LoginApi
import com.example.material_electoral.login.data.remote.service.request.UsuarioRequest
import com.example.material_electoral.login.data.remote.service.response.UsuarioResponse
import com.example.material_electoral.login.domain.repository.LoginRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class LoginRepositoryImpl  @Inject constructor(
    private val loginApi: LoginApi,
    //private val pokeApiDatabase: PokeApiDatabase
): LoginRepository {

    override suspend fun getUsuario(usuario: UsuarioRequest?): UsuarioResponse {
        return loginApi.getUsuario(usuario)
    }
}