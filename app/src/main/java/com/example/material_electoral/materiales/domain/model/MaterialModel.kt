package com.example.material_electoral.materiales.domain.model

data class MaterialModel(
    val id_casilla_material: Int,
    val id_casilla: Int,
    val id_material: Int,
    var buen_estado: Int,
    var entregado: Int,
    val nombre: String
)
