package com.example.material_electoral.caels.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.caels.ui.viewmodel.CaelsViewModel
import com.example.material_electoral.databinding.FragmentCaelsBinding
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.ui.view.dataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import com.example.material_electoral.ui.viewmodel.VMFactory

@AndroidEntryPoint
class CaelsFragment : Fragment(), RVAdapter.OnCaelClickListener {

    private var _binding:FragmentCaelsBinding? = null
    private val binding get() = _binding!!
    private val caelsViewModel: CaelsViewModel by viewModels()

    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCaelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            getDataUsuario()?.collect{
                withContext(Dispatchers.Main){
                    Log.e("usuario getdatausuario", it.toString())
                    caelsViewModel.getListOfCaels(it.id.toInt())
                }
            }
        }

        setupRecyclerView()
        setupObservers()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupObservers(){
        caelsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> {
                    binding.loader.visibility = View.VISIBLE
                }
                else -> {
                    binding.loader.visibility = View.GONE
                }
            }
        })
        caelsViewModel.listOfCaels.observe(viewLifecycleOwner, Observer {

            adapter = RVAdapter(requireContext(), it!!, this)
            binding.rvCaels.adapter = adapter
                    Log.d("LISTA CAELs", "${it}")
        })
    }

    private fun setupRecyclerView(){
        binding.rvCaels.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCaelClick(cael: CaelModel, position: Int) {
        findNavController().navigate(CaelsFragmentDirections.actionCaelsFragmentToCasillasFragment(cael))
    }

    private fun getDataUsuario() =  context?.dataStore?.data?.map {
        UsuarioModel(
            it[stringPreferencesKey("id_usuario")].orEmpty(),
            it[stringPreferencesKey("email_usuario")].orEmpty(),
            it[stringPreferencesKey("password_usuario")].orEmpty(),
            it[stringPreferencesKey("nombre_usuario")].orEmpty(),
            it[stringPreferencesKey("rol_usuario")].orEmpty(),
            it[stringPreferencesKey("token_usuario")].orEmpty()
        )
    }
}