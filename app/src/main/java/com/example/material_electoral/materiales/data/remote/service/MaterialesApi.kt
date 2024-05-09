package com.example.material_electoral.materiales.data.remote.service

import com.example.material_electoral.materiales.data.remote.service.request.ActualizarMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.request.DataMaterialRequest
import com.example.material_electoral.materiales.data.remote.service.request.GetMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.response.ActualizarMaterialesResponse
import com.example.material_electoral.materiales.data.remote.service.response.MaterialesResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface MaterialesApi {

    @POST("casillasMateriales")
    suspend fun getMateriales(@Body getMaterialesRequest: GetMaterialesRequest): MaterialesResponse

    @PUT("updateMateriales")
    suspend fun actualizarInfoMateriales(@Body actualizarMaterialesRequest: ArrayList<DataMaterialRequest>): ActualizarMaterialesResponse

}