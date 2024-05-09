package com.example.material_electoral.caels.data.remote.service.response

import com.google.gson.annotations.SerializedName

data class CrearMaterialesPorCasillaResponse(
    @SerializedName("mensaje")
    val mensaje: String,
    @SerializedName("codigo")
    val codigo: Int
)