package com.example.material_electoral.caels.data.remote.service.response.Objects

data class ResultadosCasillasMaterialesDto(
    val affectedRows: Int,
    val changedRows: Int,
    val fieldCount: Int,
    val insertId: Int,
    val message: String,
    val protocol41: Boolean,
    val serverStatus: Int,
    val warningCount: Int
)