package com.example.material_electoral.materiales.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.material_electoral.R
import com.example.material_electoral.base.BaseViewHolder
import com.example.material_electoral.databinding.MaterialRvItemBinding
import com.example.material_electoral.materiales.domain.model.MaterialModel


class MaterialesRvAdapter (private val context: Context, private val listOfMateriales: List<MaterialModel>
) :
RecyclerView.Adapter<BaseViewHolder<*>>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.material_rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfMateriales.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(listOfMateriales[position], position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<MaterialModel>(itemView){
        val binding = MaterialRvItemBinding.bind(itemView)

        override fun bind(item: MaterialModel, position: Int) {
            binding.txtNombreMaterial.text = item.nombre
            val rgEntregado = binding.rgEntregado
            val rgEstado = binding.rgEstado
            val rbEntregado = binding.rbEntregado
            val rbNoEntregado = binding.rbNoEntregado
            val rbBuenEstado= binding.rbBuenEstado
            val rbMalEstado = binding.rbMalEstado

            when(item.entregado){
                0 -> {
                    rgEntregado.check(rbNoEntregado.id)
                }
                1 -> {
                    rgEntregado.check(rbEntregado.id)
                }

            }
            when(item.buen_estado){
                0 -> {
                    rgEstado.check(rbMalEstado.id)
                }
                1 -> {
                    rgEstado.check(rbBuenEstado.id)
                }
            }

            rgEntregado.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    rbEntregado.id -> {
                        item.entregado = 1
                    }
                    rbNoEntregado.id -> {
                        item.entregado = 0
                    }
                }
            }

            rgEstado.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    rbBuenEstado.id -> {
                        item.buen_estado = 1
                    }
                    rbMalEstado.id -> {
                        item.buen_estado = 0
                    }
                }
            }
        }
    }

    fun getListOfMateriales(): List<MaterialModel> {
        return listOfMateriales
    }
}