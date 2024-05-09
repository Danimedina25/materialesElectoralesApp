package com.example.material_electoral.materiales.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.material_electoral.login.domain.util.Resource
import com.example.material_electoral.materiales.data.remote.service.request.DataMaterialRequest
import com.example.material_electoral.materiales.data.remote.service.request.GetMaterialesRequest
import com.example.material_electoral.materiales.data.remote.service.response.ActualizarMaterialesResponse
import com.example.material_electoral.materiales.domain.model.MaterialModel
import com.example.material_electoral.materiales.domain.usecases.GetListOfMaterialesUseCase
import com.example.material_electoral.materiales.domain.usecases.UpdateMaterialesUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialesViewModel  @Inject constructor(
    private val getListOfMaterialesUseCase: GetListOfMaterialesUseCase,
    private val updateMaterialesUseCase: UpdateMaterialesUseCase,
): ViewModel() {

    private val _listOfMateriales = MutableLiveData<List<MaterialModel>?>(emptyList())
    val listOfMateriales: LiveData<List<MaterialModel>?>
        get() = _listOfMateriales

    private val _resultUpdateMateriales = MutableLiveData<ActualizarMaterialesResponse>()
    val resultUpdateMateriales: LiveData<ActualizarMaterialesResponse>
        get() = _resultUpdateMateriales

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun getListOfMateriales(idCasilla: Int) {
        val getMaterialesRequest = GetMaterialesRequest(idCasilla)
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = getListOfMaterialesUseCase(getMaterialesRequest)) {
                is Resource.Success -> {
                    Log.d("list of materiales result", Gson().toJson(result))
                    _isLoading.value = false
                    _listOfMateriales.value = result.data
                }

                is Resource.Error -> {
                    Log.d("list of materiales", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }

    fun updateMateriales(dataMaterialesRequest: ArrayList<DataMaterialRequest>) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = updateMaterialesUseCase(dataMaterialesRequest)) {
                is Resource.Success -> {
                    Log.d("update materials result", Gson().toJson(result))
                    _isLoading.value = false
                    _resultUpdateMateriales.value = result.data!!
                }

                is Resource.Error -> {
                    Log.d("update materials error", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }

    fun getUpdateMaterialesRequest(listOfMateriales: List<MaterialModel>): ArrayList<DataMaterialRequest> {
        val dataMaterialesRequest = ArrayList<DataMaterialRequest>()
        for(material in listOfMateriales){
            val dataMaterialRequest = DataMaterialRequest(material.id_casilla_material, material.entregado, material.buen_estado)
            dataMaterialesRequest.add(dataMaterialRequest)
        }
        return dataMaterialesRequest
    }
}
