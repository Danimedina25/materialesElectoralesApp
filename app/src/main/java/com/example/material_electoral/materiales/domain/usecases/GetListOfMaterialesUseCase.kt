package com.example.material_electoral.materiales.domain.usecases

import com.example.material_electoral.login.domain.util.Resource
import com.example.material_electoral.materiales.data.mappers.toDomain
import com.example.material_electoral.materiales.data.remote.service.request.GetMaterialesRequest
import com.example.material_electoral.materiales.domain.model.MaterialModel
import com.example.material_electoral.materiales.domain.repository.MaterialesRepository
import javax.inject.Inject

class GetListOfMaterialesUseCase @Inject constructor(private val repository: MaterialesRepository) {

    suspend operator fun invoke(getMaterialesRequest: GetMaterialesRequest): Resource<List<MaterialModel>> {
        return try {
            val result = repository.getMateriales(getMaterialesRequest)
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