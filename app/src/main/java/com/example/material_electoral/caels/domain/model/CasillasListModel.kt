package com.example.material_electoral.caels.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CasillasListModel(
    var listOfCasillas: List<CasillaModel>
    ): Parcelable

