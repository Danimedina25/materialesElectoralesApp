package com.example.material_electoral.caels.ui.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.material_electoral.MainActivity
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.caels.domain.model.CasillaModel
import com.example.material_electoral.caels.domain.model.CasillasListModel
import com.example.material_electoral.caels.ui.viewmodel.CaelsViewModel
import com.example.material_electoral.databinding.FragmentCasillasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CasillasFragment : Fragment(), RVCasillasListAdapter.OnCasillasClickListener {

    private var _binding: FragmentCasillasBinding? = null
    private val binding get() = _binding!!
    private val caelsViewModel: CaelsViewModel by viewModels()

    private lateinit var adapter: RVCasillasListAdapter

    private lateinit var caelModel: CaelModel
    private var casillasListModel: CasillasListModel = CasillasListModel(emptyList())
    private lateinit var currentCasillaModel: CasillaModel
    private val navigationArgs : CasillasFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCasillasBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caelModel = navigationArgs.CaelData

        (activity as MainActivity).supportActionBar?.title = "Casillas ${caelModel.caelNombre}"

        caelsViewModel.getListOfCasillas(caelModel.caelId)

        setupObservers()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupObservers(){

        caelsViewModel.isLoading.observe(viewLifecycleOwner) {
           when(it){
               true -> {
                   binding.loader.visibility = View.VISIBLE
               }
               else -> {
                   binding.loader.visibility = View.GONE
               }
           }
        }

        caelsViewModel.listOfCasillas.observe(viewLifecycleOwner) {
            if (it != null) {
                casillasListModel.listOfCasillas = it
                setupRecyclerView()
            }

        }

        caelsViewModel.resultUpdateEstatusCasillaEntregada.observe(viewLifecycleOwner) {
            if (it!!.codigo == 0) {
                Toast.makeText(requireContext(), it.mensaje, Toast.LENGTH_SHORT).show()
                clearListOfCasillas()
                caelsViewModel.clearListOfCasillas()
                caelsViewModel.getListOfCasillas(caelModel.caelId)
            }
        }


        caelsViewModel.showListaMateriales.observe(viewLifecycleOwner){
            if(it){
                caelsViewModel.setFalseShowListaMateriales()
                findNavController().navigate(CasillasFragmentDirections.actionCasillasFragmentToMaterialesPorCasillaFragment(caelModel, currentCasillaModel))
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvCasillas.layoutManager = LinearLayoutManager(requireContext())
        adapter = RVCasillasListAdapter(requireContext(), casillasListModel.listOfCasillas, this)
        binding.rvCasillas.adapter = adapter
        Log.d("LISTA Casillas", "${casillasListModel}")
        binding.txtTotalCasillasEntregadas.text = "Entregadas: ${adapter.getTotalCasillasEntregadas()}/${adapter.itemCount}"
    }

    override fun onClickButtonMateriales(casillaModel: CasillaModel, position: Int) {
        currentCasillaModel = casillaModel
        caelsViewModel.crearListaMaterialesPorCasilla(currentCasillaModel.id_casilla)
    }

    override fun onUpdateEstatusCasillaEntregada(casillaModel: CasillaModel, nuevoEstatusEntregada: Int) {
       showConfirmUpdateEstatusCasillaEntregada(nuevoEstatusEntregada, casillaModel.id_casilla, casillaModel.nombre)
    }

    fun clearListOfCasillas(){
        casillasListModel.listOfCasillas = emptyList()
    }

    fun showConfirmUpdateEstatusCasillaEntregada(NuevoEstatusEntregada: Int, idCasilla: Int, nombreCasilla: String){
        var nuevoEstatusText = ""
        when(NuevoEstatusEntregada) {
            1 -> {
                nuevoEstatusText = "entregada"
            }
            0 -> {
                nuevoEstatusText = "no entregada"
            }
        }
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Deseas cambiar el estatus de la casilla ${nombreCasilla.toLowerCase()} a ${nuevoEstatusText}?")
            .setCancelable(false)
            .setPositiveButton("Sí") { dialog, id ->
                caelsViewModel.updateEstatusCasillaEntregada(NuevoEstatusEntregada, idCasilla)
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}