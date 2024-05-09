package com.example.material_electoral.login.domain.usecases

import com.example.material_electoral.login.data.mappers.toDomain
import com.example.material_electoral.login.data.remote.service.request.UsuarioRequest
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.domain.repository.LoginRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(usuarioRequest: UsuarioRequest?): Resource<UsuarioModel> {
        return try {
            val result = repository.getUsuario(usuarioRequest)
            return when(result.codigo){
                0 -> {
                    Resource.Success(result.toDomain())
                }
                else -> {
                    Resource.Error(result.mensaje)
                }
            }
        }catch (e:Exception){
            Resource.Error(e.message)
        }
    }
}