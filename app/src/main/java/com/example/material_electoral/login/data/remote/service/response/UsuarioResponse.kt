package com.example.material_electoral.login.data.remote.service.response

import android.os.Parcelable
import com.example.material_electoral.login.data.remote.service.response.Objects.UsuarioDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UsuarioResponse (
        @SerializedName("usuario")
        val usuario: UsuarioDto,
        @SerializedName("codigo")
        val codigo: Int,
        @SerializedName("mensaje")
        val mensaje: String
)
