package com.example.material_electoral.materiales.data.remote.service.request

import com.google.gson.annotations.SerializedName

data class DataMaterialRequest(
    @SerializedName("id_casilla_material")
    val idCasillaMaterial: Int,
    val entregado: Int,
    val buen_estado: Int
)

class ActualizarMaterialesRequest: ArrayList<DataMaterialRequest>()