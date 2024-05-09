package com.example.material_electoral.materiales.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.caels.domain.model.CasillaModel
import com.example.material_electoral.databinding.FragmentMaterialesPorCasillaBinding
import com.example.material_electoral.materiales.ui.viewmodel.MaterialesViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterialesFragment : Fragment() {

    private var _binding:FragmentMaterialesPorCasillaBinding? = null
    private val binding get() = _binding!!
    private val materialesViewModel: MaterialesViewModel by viewModels()
    private lateinit var caelModel: CaelModel
    private lateinit var casillaModel: CasillaModel
    private val navigationArgs : MaterialesFragmentArgs by navArgs()

    private lateinit var adapter: MaterialesRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMaterialesPorCasillaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caelModel = navigationArgs.CaelData
        casillaModel = navigationArgs.CasillaData

        materialesViewModel.getListOfMateriales(casillaModel.id_casilla)
        setupRecyclerView()
        setupObservers()

        binding.txtNombreCael.text = caelModel.caelNombre
        binding.txtNombreCasilla.text = casillaModel.nombre

        binding.btnGuardar.setOnClickListener {
            materialesViewModel.updateMateriales(materialesViewModel.getUpdateMaterialesRequest(adapter.getListOfMateriales()))
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupObservers(){
        materialesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> {
                    binding.loader.visibility = View.VISIBLE
                }
                else -> {
                    binding.loader.visibility = View.GONE
                }
            }
        })
        materialesViewModel.listOfMateriales.observe(viewLifecycleOwner, Observer {

            adapter = MaterialesRvAdapter(requireContext(), it!!)
            binding.rvMateriales.adapter = adapter
                    Log.d("LISTA Materiales", "${it}")
        })

        materialesViewModel.resultUpdateMateriales.observe(viewLifecycleOwner, Observer {
            if(it.codigo == 0){
                Toast.makeText(requireContext(), it.mensaje, Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        })
    }

    private fun setupRecyclerView(){
        binding.rvMateriales.layoutManager = LinearLayoutManager(requireContext())
    }
}