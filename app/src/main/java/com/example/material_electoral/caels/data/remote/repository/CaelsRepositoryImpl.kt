package com.example.material_electoral.caels.data.remote.repository

import com.example.material_electoral.caels.data.remote.service.CaelsApi
import com.example.material_electoral.caels.data.remote.service.request.CaelsRequest
import com.example.material_electoral.caels.data.remote.service.request.CrearMaterialesPorCasillaRequest
import com.example.material_electoral.caels.data.remote.service.request.GetCasillasRequest
import com.example.material_electoral.caels.data.remote.service.request.UpdateEstatusCasillaEntregadaRequest
import com.example.material_electoral.caels.data.remote.service.response.CaelsListResponse
import com.example.material_electoral.caels.data.remote.service.response.CasillasResponse
import com.example.material_electoral.caels.data.remote.service.response.CrearMaterialesPorCasillaResponse
import com.example.material_electoral.caels.data.remote.service.response.UpdateEstatusCasillaEntregadaResponse
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import javax.inject.Inject

class CaelsRepositoryImpl  @Inject constructor(
    private val caelsApi: CaelsApi,
): CaelsRepository {

    override suspend fun getCaelsList(caelsRequest: CaelsRequest): CaelsListResponse {
        return caelsApi.getCaels(caelsRequest)
    }

    override suspend fun crearMaterialesPorCasilla(crearMaterialesPorCasillaRequest: CrearMaterialesPorCasillaRequest): CrearMaterialesPorCasillaResponse {
        return caelsApi.registrarMaterialesPorCasilla(crearMaterialesPorCasillaRequest)
    }

    override suspend fun getCasillas(getCasillasRequest: GetCasillasRequest): CasillasResponse {
        return caelsApi.getCasillas(getCasillasRequest)
    }

    override suspend fun updateStatusCasillaEntregada(updateEstatusCasillaEntregadaRequest: UpdateEstatusCasillaEntregadaRequest): UpdateEstatusCasillaEntregadaResponse {
        return caelsApi.updateEstatusCasillaEntregada(updateEstatusCasillaEntregadaRequest)
    }
}