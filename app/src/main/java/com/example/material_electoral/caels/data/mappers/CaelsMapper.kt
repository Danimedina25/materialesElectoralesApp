package com.example.material_electoral.caels.data.mappers

import com.example.material_electoral.caels.data.remote.service.response.CaelsListResponse
import com.example.material_electoral.caels.domain.model.CaelModel

fun CaelsListResponse.toDomain(): List<CaelModel> {
    val listOfCaels = caelsList.mapIndexed { _, cael ->
        CaelModel(
           caelId = cael.caelsId,
            caelNombre = cael.caelsNombre,
            area_responsabilidad = cael.area_responsabilidad,
            id_supervisor = cael.id_supervisor,
            observaciones = cael.observaciones,
            casillas_asignadas = cael.casillas_asignadas,
            casillas_entregadas = cael.casillas_entregadas
        )
    }
    return listOfCaels
}