package com.example.material_electoral.login.data.remote.service.response.Objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsuarioDto (
        @SerializedName("id")
        val id:String = "",
        @SerializedName("email")
        val email:String = "",
        @SerializedName("password")
        val password:String = "",
        @SerializedName("nombre")
        val nombre:String = "",
        @SerializedName("rol")
        val rol:String = "",
        @SerializedName("token")
        val token:String = ""
): Parcelable
