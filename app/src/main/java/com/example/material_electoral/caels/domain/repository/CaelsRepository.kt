package com.example.material_electoral.caels.domain.repository

import com.example.material_electoral.caels.data.remote.service.request.CaelsRequest
import com.example.material_electoral.caels.data.remote.service.request.CrearMaterialesPorCasillaRequest
import com.example.material_electoral.caels.data.remote.service.request.GetCasillasRequest
import com.example.material_electoral.caels.data.remote.service.request.UpdateEstatusCasillaEntregadaRequest
import com.example.material_electoral.caels.data.remote.service.response.CaelsListResponse
import com.example.material_electoral.caels.data.remote.service.response.CasillasResponse
import com.example.material_electoral.caels.data.remote.service.response.CrearMaterialesPorCasillaResponse
import com.example.material_electoral.caels.data.remote.service.response.UpdateEstatusCasillaEntregadaResponse


interface CaelsRepository {
    suspend fun getCaelsList(caelsRequest: CaelsRequest): CaelsListResponse

    suspend fun crearMaterialesPorCasilla(crearMaterialesPorCasillaRequest: CrearMaterialesPorCasillaRequest): CrearMaterialesPorCasillaResponse

    suspend fun getCasillas(getCasillasRequest: GetCasillasRequest): CasillasResponse

    suspend fun updateStatusCasillaEntregada(updateEstatusCasillaEntregadaRequest: UpdateEstatusCasillaEntregadaRequest): UpdateEstatusCasillaEntregadaResponse
}

