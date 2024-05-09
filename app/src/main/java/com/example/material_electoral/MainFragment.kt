package com.example.material_electoral

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.databinding.FragmentMainBinding
import com.example.material_electoral.login.ui.view.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //metodos

        lifecycleScope.launch(Dispatchers.IO) {
            getDataUsuario()?.collect{
                withContext(Dispatchers.Main){
                    Log.e("usuario getdatausuario", it.toString())
                    binding.txtUsuario.text = it.nombre

                    if(it.rol != "Admin"){
                        binding.btnIrSupervisores.visibility = View.VISIBLE
                        binding.btnIrCaels.visibility = View.GONE
                    }else{
                        binding.btnIrSupervisores.visibility = View.GONE
                        binding.btnIrCaels.visibility = View.VISIBLE
                    }
                }
            }
        }



        //binding.txtNombreUsuario.text = (activity as MainActivity).usuario.nombre

        binding.btnIrCaels.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_caelsFragment)
        }
    }

    private fun getDataUsuario() = context?.dataStore?.data?.map {
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