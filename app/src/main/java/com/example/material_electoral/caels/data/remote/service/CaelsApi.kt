package com.example.material_electoral.caels.data.remote.service

import com.example.material_electoral.caels.data.remote.service.request.CaelsRequest
import com.example.material_electoral.caels.data.remote.service.request.CrearMaterialesPorCasillaRequest
import com.example.material_electoral.caels.data.remote.service.request.GetCasillasRequest
import com.example.material_electoral.caels.data.remote.service.request.UpdateEstatusCasillaEntregadaRequest
import com.example.material_electoral.caels.data.remote.service.response.CaelsListResponse
import com.example.material_electoral.caels.data.remote.service.response.CasillasResponse
import com.example.material_electoral.caels.data.remote.service.response.CrearMaterialesPorCasillaResponse
import com.example.material_electoral.caels.data.remote.service.response.UpdateEstatusCasillaEntregadaResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CaelsApi {

    @POST("caels")
    suspend fun getCaels(@Body caelsRequest: CaelsRequest): CaelsListResponse

    @POST("crearMaterialesPorCasilla")
    suspend fun registrarMaterialesPorCasilla(@Body crearMaterialesPorCasillaRequest: CrearMaterialesPorCasillaRequest): CrearMaterialesPorCasillaResponse

    @POST("casillas")
    suspend fun getCasillas(@Body getCasillasRequest: GetCasillasRequest): CasillasResponse

    @PUT("updateStatusCasillaEntregada")
    suspend fun updateEstatusCasillaEntregada(@Body updateEstatusCasillaEntregadaRequest: UpdateEstatusCasillaEntregadaRequest): UpdateEstatusCasillaEntregadaResponse
}