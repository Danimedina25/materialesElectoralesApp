package com.example.material_electoral.caels.data.remote.service.response

import com.example.material_electoral.caels.data.remote.service.response.Objects.CasillaDto
import com.google.gson.annotations.SerializedName

data class CasillasResponse(
    @SerializedName("casillas")
    val casillas: List<CasillaDto>,
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("mensaje")
    val mensaje: String
)