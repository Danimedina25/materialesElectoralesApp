package com.example.material_electoral.caels.domain.usecases

import com.example.material_electoral.caels.data.mappers.toDomain
import com.example.material_electoral.caels.data.remote.service.request.CaelsRequest
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import com.example.material_electoral.login.domain.util.Resource
import javax.inject.Inject

class GetListOfCaelsUseCase @Inject constructor(private val repository: CaelsRepository) {

    suspend operator fun invoke(caelsRequest: CaelsRequest): Resource<List<CaelModel>> {
        return try {
            val result = repository.getCaelsList(caelsRequest)
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