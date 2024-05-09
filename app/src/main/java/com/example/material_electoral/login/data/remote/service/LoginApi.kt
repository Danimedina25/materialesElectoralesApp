package com.example.material_electoral.login.data.remote.service
import com.example.material_electoral.login.data.remote.service.request.UsuarioRequest
import com.example.material_electoral.login.data.remote.service.response.UsuarioResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun getUsuario(@Body usuario: UsuarioRequest?): UsuarioResponse
}