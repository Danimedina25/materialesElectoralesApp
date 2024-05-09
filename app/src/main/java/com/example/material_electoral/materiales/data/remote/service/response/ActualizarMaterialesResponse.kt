package com.example.material_electoral.materiales.data.remote.service.response

import com.example.material_electoral.caels.data.remote.service.response.Objects.CasillaDto
import com.example.material_electoral.materiales.data.remote.service.response.Objects.MaterialDto
import com.google.gson.annotations.SerializedName

data class ActualizarMaterialesResponse(
    @SerializedName("mensaje")
    val mensaje: String,
    @SerializedName("codigo")
    val codigo: Int,
)