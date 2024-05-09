package com.example.material_electoral.materiales.data.remote.repository

import com.example.material_electoral.materiales.data.mappers.toDomain
import com.example.material_electoral.materiales.data.remote.service.MaterialesApi
import com.example.material_electoral.materiales.data.remote.service.request.ActualizarMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.request.DataMaterialRequest
import com.example.material_electoral.materiales.data.remote.service.request.GetMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.response.ActualizarMaterialesResponse
import com.example.material_electoral.materiales.data.remote.service.response.MaterialesResponse
import com.example.material_electoral.materiales.domain.model.MaterialModel
import com.example.material_electoral.materiales.domain.repository.MaterialesRepository
import javax.inject.Inject

class MaterialesRepositoryImpl  @Inject constructor(
    private val materialesApi: MaterialesApi,
): MaterialesRepository {

    override suspend fun getMateriales(getMaterialesRequest: GetMaterialesRequest): MaterialesResponse {
        return materialesApi.getMateriales(getMaterialesRequest)
    }

    override suspend fun actualizarMateriales(actualizarMaterialesRequest: ArrayList<DataMaterialRequest>): ActualizarMaterialesResponse {
        return materialesApi.actualizarInfoMateriales(actualizarMaterialesRequest)
    }

}