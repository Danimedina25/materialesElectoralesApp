package com.example.material_electoral.caels.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaelModel(
    val caelId:Int = 0,
    val caelNombre:String = "",
    val area_responsabilidad:String = "",
    val id_supervisor: Int = 0,
    val observaciones:String = "",
    val casillas_asignadas: Int = 0,
    val casillas_entregadas: Int = 0,
    ): Parcelable

