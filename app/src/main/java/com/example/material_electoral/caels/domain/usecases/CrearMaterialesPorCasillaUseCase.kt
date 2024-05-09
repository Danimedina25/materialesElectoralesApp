package com.example.material_electoral.caels.domain.usecases

import com.example.material_electoral.caels.data.remote.service.request.CrearMaterialesPorCasillaRequest
import com.example.material_electoral.caels.data.remote.service.response.CrearMaterialesPorCasillaResponse
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class CrearMaterialesPorCasillaUseCase @Inject constructor(private val repository: CaelsRepository) {

    suspend operator fun invoke(crearMaterialesPorCasillaRequest: CrearMaterialesPorCasillaRequest): Resource<CrearMaterialesPorCasillaResponse> {
        return try {
            val result = repository.crearMaterialesPorCasilla(crearMaterialesPorCasillaRequest)
            when(result.codigo){
                -1 -> {
                    Resource.Error(result.mensaje)
                }
                else -> {
                    Resource.Success(
                        data = result
                    )
                }
            }
        } catch (e: Exception) {
            (Resource.Error(
                e.message
            ))
        }
    }
}