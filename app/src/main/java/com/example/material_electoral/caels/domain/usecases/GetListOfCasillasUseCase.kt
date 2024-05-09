package com.example.material_electoral.caels.domain.usecases

import com.example.material_electoral.caels.data.mappers.toDomain
import com.example.material_electoral.caels.data.remote.service.request.GetCasillasRequest
import com.example.material_electoral.caels.domain.model.CasillaModel
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class GetListOfCasillasUseCase @Inject constructor(private val repository: CaelsRepository) {

    suspend operator fun invoke(getCasillasRequest: GetCasillasRequest): Resource<List<CasillaModel>> {
        return try {
            val result = repository.getCasillas(getCasillasRequest)
            when(result.codigo){
                0 -> {
                    Resource.Success(
                        data = result.toDomain()
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