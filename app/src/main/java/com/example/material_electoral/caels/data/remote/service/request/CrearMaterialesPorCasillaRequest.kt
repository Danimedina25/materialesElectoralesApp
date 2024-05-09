package com.example.material_electoral.caels.data.remote.service.request

import com.example.material_electoral.caels.domain.model.CasillaModel
import com.google.gson.annotations.SerializedName


data class CrearMaterialesPorCasillaRequest(
    @SerializedName("idCasilla")
    val idCasilla: Int,
)
