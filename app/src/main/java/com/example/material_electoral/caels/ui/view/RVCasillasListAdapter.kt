package com.example.material_electoral.caels.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.example.material_electoral.R
import com.example.material_electoral.base.BaseViewHolder
import com.example.material_electoral.caels.domain.model.CasillaModel
import com.example.material_electoral.databinding.CasillaRvItemBinding


class RVCasillasListAdapter (private val context: Context, private val casillasList:List<CasillaModel>,
                             private val itemCLickListener: OnCasillasClickListener,
) :
RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnCasillasClickListener{
        fun onClickButtonMateriales(casillaModel: CasillaModel, position: Int)
        fun onUpdateEstatusCasillaEntregada(casillaModel: CasillaModel, NuevoEstatusEntregada: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.casilla_rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return casillasList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(casillasList[position], position)
        }
    }

    fun getTotalCasillasEntregadas():Int{
        var totalCasillasEntregadas = 0
        for (casilla in casillasList){
            if(casilla.estatus_entregada == 1){
                totalCasillasEntregadas += 1
            }
        }
        return totalCasillasEntregadas
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<CasillaModel>(itemView){
        val binding = CasillaRvItemBinding.bind(itemView)

        override fun bind(item: CasillaModel, position: Int) {
            when(item.estatus_entregada) {
                0 -> {
                    binding.btnMateriales.visibility = View.INVISIBLE
                    binding.rgEntregada.check(binding.rbNoEntregada.id)
                }
                1 -> {
                    binding.rgEntregada.check(binding.rbEntregada.id)
                    binding.btnMateriales.visibility = View.VISIBLE
                }

            }
            binding.rgEntregada.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    binding.rbEntregada.id -> {
                        itemCLickListener.onUpdateEstatusCasillaEntregada(item,1)
                    }
                    binding.rbNoEntregada.id -> {
                        itemCLickListener.onUpdateEstatusCasillaEntregada(item, 0)
                    }
                }
            }
            binding.btnMateriales.setOnClickListener { itemCLickListener.onClickButtonMateriales(item, position) }
            binding.txtNombreCasilla.text = "${item.nombre.toLowerCase()}"
        }

    }
}