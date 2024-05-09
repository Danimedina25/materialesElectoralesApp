package com.example.material_electoral.materiales.data.mappers

import com.example.material_electoral.materiales.data.remote.service.response.MaterialesResponse
import com.example.material_electoral.materiales.domain.model.MaterialModel


fun MaterialesResponse.toDomain(): List<MaterialModel> {
    val listOfMateriales = materiales.mapIndexed { _, material ->
        MaterialModel(
            material.id_casilla_material,
            material.id_casilla,
            material.id_material,
            material.buen_estado,
            material.entregado,
            material.nombre
        )
    }
    return listOfMateriales
}