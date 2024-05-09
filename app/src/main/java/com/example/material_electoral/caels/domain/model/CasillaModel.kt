package com.example.material_electoral.caels.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CasillaModel(
    val id_casilla: Int,
    val id_cael: Int,
    val nombre: String,
    val estatus_entregada: Int
    ): Parcelable

