package com.example.material_electoral.caels.data.remote.service.response

import com.example.material_electoral.caels.data.remote.service.response.Objects.CasillaDto
import com.google.gson.annotations.SerializedName

data class UpdateEstatusCasillaEntregadaResponse(
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("mensaje")
    val mensaje: String
)