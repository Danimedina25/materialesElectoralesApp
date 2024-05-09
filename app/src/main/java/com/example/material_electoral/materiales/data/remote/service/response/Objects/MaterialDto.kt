package com.example.material_electoral.materiales.data.remote.service.response.Objects

data class MaterialDto(
    val id_casilla_material: Int,
    val id_casilla: Int,
    val id_material: Int,
    val buen_estado: Int,
    val entregado: Int,
    val nombre: String
)