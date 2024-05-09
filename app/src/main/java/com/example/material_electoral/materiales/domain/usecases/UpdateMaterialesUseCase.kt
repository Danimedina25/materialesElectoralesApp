package com.example.material_electoral.materiales.domain.usecases

import com.example.material_electoral.login.domain.util.Resource
import com.example.material_electoral.materiales.data.mappers.toDomain
import com.example.material_electoral.materiales.data.remote.service.request.DataMaterialRequest
import com.example.material_electoral.materiales.data.remote.service.response.ActualizarMaterialesResponse
import com.example.material_electoral.materiales.domain.repository.MaterialesRepository
import javax.inject.Inject

class UpdateMaterialesUseCase @Inject constructor(private val repository: MaterialesRepository) {

    suspend operator fun invoke(dataMaterialRequest: ArrayList<DataMaterialRequest>): Resource<ActualizarMaterialesResponse> {
        return try {
            val result = repository.actualizarMateriales(dataMaterialRequest)
            return when(result.codigo){
                0 -> {
                    Resource.Success(result)
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