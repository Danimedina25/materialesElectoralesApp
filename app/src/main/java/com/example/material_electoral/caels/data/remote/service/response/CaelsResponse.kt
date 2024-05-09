package com.example.material_electoral.caels.data.remote.service.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CaelsResponse(
    @SerializedName("id")
    val caelsId:Int = 0,
    @SerializedName("nombre")
    val caelsNombre:String = "",
    @SerializedName("area_responsabilidad")
    val area_responsabilidad:String = "",
    @SerializedName("id_supervisor")
    val  id_supervisor: Int = 0,
    @SerializedName("observaciones")
    val observaciones:String = "",
    @SerializedName("casillas_asignadas")
    val casillas_asignadas: Int = 0,
    @SerializedName("casillas_entregadas")
    val casillas_entregadas: Int = 0,
    ):Parcelable

data class CaelsListResponse(
    @SerializedName("caels")
    val caelsList:List<CaelsResponse> = emptyList(),
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("mensaje")
    val mensaje: String
)