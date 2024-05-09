package com.example.material_electoral.materiales.data.remote.service.response

import com.example.material_electoral.caels.data.remote.service.response.Objects.CasillaDto
import com.example.material_electoral.materiales.data.remote.service.response.Objects.MaterialDto
import com.google.gson.annotations.SerializedName

data class MaterialesResponse(
    @SerializedName("materiales")
    val materiales: List<MaterialDto>,
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("mensaje")
    val mensaje: String
)