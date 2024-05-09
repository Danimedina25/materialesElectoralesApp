package com.example.material_electoral.caels.ui.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.material_electoral.caels.data.remote.service.request.CaelsRequest
import com.example.material_electoral.caels.data.remote.service.request.CrearMaterialesPorCasillaRequest
import com.example.material_electoral.caels.data.remote.service.request.GetCasillasRequest
import com.example.material_electoral.caels.data.remote.service.request.UpdateEstatusCasillaEntregadaRequest
import com.example.material_electoral.caels.data.remote.service.response.CrearMaterialesPorCasillaResponse
import com.example.material_electoral.caels.data.remote.service.response.UpdateEstatusCasillaEntregadaResponse
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.caels.domain.model.CasillaModel
import com.example.material_electoral.caels.domain.usecases.GetListOfCaelsUseCase
import com.example.material_electoral.caels.domain.usecases.GetListOfCasillasUseCase
import com.example.material_electoral.caels.domain.usecases.CrearMaterialesPorCasillaUseCase
import com.example.material_electoral.caels.domain.usecases.UpdateEstatusCasillaEntregadaUseCase
import com.example.material_electoral.login.domain.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaelsViewModel  @Inject constructor(
    private val getListOfCaelsUseCase: GetListOfCaelsUseCase,
    private val crearMaterialesPorCasillaUseCase: CrearMaterialesPorCasillaUseCase,
    private val getListOfCasillasUseCase: GetListOfCasillasUseCase,
    private val updateEstatusCasillaEntregadaUseCase: UpdateEstatusCasillaEntregadaUseCase
): ViewModel(){

    private val _listOfCaels = MutableLiveData<List<CaelModel>?>(emptyList())
    val listOfCaels: LiveData<List<CaelModel>?>
        get() = _listOfCaels

    private val _showListaMateriales = MutableLiveData<Boolean>(false)
    val showListaMateriales: LiveData<Boolean>
        get() = _showListaMateriales

    private val _listOfCasillas = MutableLiveData<List<CasillaModel>?>(emptyList())
    val listOfCasillas: LiveData<List<CasillaModel>?>
        get() = _listOfCasillas

    private val _resultUpdateEstatusCasillaEntregada = MutableLiveData<UpdateEstatusCasillaEntregadaResponse?>()
    val resultUpdateEstatusCasillaEntregada: LiveData<UpdateEstatusCasillaEntregadaResponse?>
        get() = _resultUpdateEstatusCasillaEntregada

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun clearListOfCasillas(){
        _listOfCasillas.value = emptyList()
    }

    fun setFalseShowListaMateriales(){
        _showListaMateriales.value = false
    }

    fun getListOfCaels(idSupervisor: Int){
        val caelsRequest = CaelsRequest(idSupervisor)
        _isLoading.value = true
        viewModelScope.launch {
            when(val result = getListOfCaelsUseCase(caelsRequest)){
                is Resource.Success -> {
                    Log.d("list of caels result", Gson().toJson(result))
                    _isLoading.value = false
                    _listOfCaels.value = result.data
                }
                is Resource.Error -> {
                    Log.d("list of caels", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }
    fun crearListaMaterialesPorCasilla(idCasilla:Int){
        _isLoading.value = true
        val crearMaterialesPorCasillaRequest = CrearMaterialesPorCasillaRequest(idCasilla)
        viewModelScope.launch {
            when(val result = crearMaterialesPorCasillaUseCase(crearMaterialesPorCasillaRequest)){
                is Resource.Success -> {
                    Log.d("registro materiales result", Gson().toJson(result))
                    _isLoading.value = false
                    _showListaMateriales.value = result.data.codigo == 0 || result.data.codigo == 1
                }
                is Resource.Error -> {
                    Log.d("registro materiales error", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }

    fun getListOfCasillas(idCael: Int){
        _isLoading.value = true
        val getCasillasRequest = GetCasillasRequest(idCael)
        viewModelScope.launch {
            when(val result = getListOfCasillasUseCase(getCasillasRequest)){
                is Resource.Success -> {
                    Log.d("list of casillas result", Gson().toJson(result))
                    _isLoading.value = false
                    _listOfCasillas.value = result.data
                }
                is Resource.Error -> {
                    Log.d("list of casillas", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }

    fun updateEstatusCasillaEntregada(nuevoEstatusEntregada: Int, idCasilla: Int){
        _isLoading.value = true
        val updateEstatusCasillaEntregadaRequest = UpdateEstatusCasillaEntregadaRequest(nuevoEstatusEntregada, idCasilla)
        viewModelScope.launch {
            when(val result = updateEstatusCasillaEntregadaUseCase(updateEstatusCasillaEntregadaRequest)){
                is Resource.Success -> {
                    Log.d("update estatus casilla result", Gson().toJson(result))
                    _isLoading.value = false
                    _resultUpdateEstatusCasillaEntregada.value = result.data
                }
                is Resource.Error -> {
                    Log.d("update estatus casilla", Gson().toJson(result))
                    _error.value = result.mensaje
                    _isLoading.value = false
                }
            }
        }
    }
}
