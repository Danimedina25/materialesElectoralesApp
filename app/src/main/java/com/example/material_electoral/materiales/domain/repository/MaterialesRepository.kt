package com.example.material_electoral.materiales.domain.repository

import com.example.material_electoral.materiales.data.remote.service.request.DataMaterialRequest
import com.example.material_electoral.materiales.data.remote.service.request.GetMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.response.ActualizarMaterialesResponse
import com.example.material_electoral.materiales.data.remote.service.response.MaterialesResponse
import com.example.material_electoral.materiales.domain.model.MaterialModel

interface MaterialesRepository {
    suspend fun getMateriales(getMaterialesRequest: GetMaterialesRequest): MaterialesResponse

    suspend fun actualizarMateriales(actualizarMaterialesRequest: ArrayList<DataMaterialRequest>): ActualizarMaterialesResponse
}

