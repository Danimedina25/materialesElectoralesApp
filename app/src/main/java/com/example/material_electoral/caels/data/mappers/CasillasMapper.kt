package com.example.material_electoral.caels.data.mappers

import com.example.material_electoral.caels.data.remote.service.response.CasillasResponse
import com.example.material_electoral.caels.domain.model.CasillaModel


fun CasillasResponse.toDomain(): List<CasillaModel> {
    val listOfCasillas = casillas.mapIndexed { _, casilla ->
        CasillaModel(
            casilla.id_casilla,
            casilla.id_cael,
            casilla.nombre_casilla,
            casilla.estatus_entregada
        )
    }
    return listOfCasillas
}