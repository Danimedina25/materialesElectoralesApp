package com.example.material_electoral.caels.domain.usecases

import com.example.material_electoral.caels.data.remote.service.request.UpdateEstatusCasillaEntregadaRequest
import com.example.material_electoral.caels.data.remote.service.response.UpdateEstatusCasillaEntregadaResponse
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class UpdateEstatusCasillaEntregadaUseCase @Inject constructor(private val repository: CaelsRepository) {

    suspend operator fun invoke(updateEstatusCasillaEntregadaRequest: UpdateEstatusCasillaEntregadaRequest): Resource<UpdateEstatusCasillaEntregadaResponse> {
        return try {
            val result = repository.updateStatusCasillaEntregada(updateEstatusCasillaEntregadaRequest)
            when(result.codigo){
                0 -> {
                    Resource.Success(
                        data = result
                    )
                }
                else -> {
                    Resource.Error(result.mensaje)
                }
            }
        } catch (e: Exception) {
            (Resource.Error(
                e.message
            ))
        }
    }
}