package com.example.material_electoral.caels.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.material_electoral.R
import com.example.material_electoral.base.BaseViewHolder
import com.example.material_electoral.caels.domain.model.CaelModel
import com.example.material_electoral.databinding.CaelRvItemBinding


class RVAdapter (private val context: Context, private val caelsList:List<CaelModel>,
                 private val itemCLickListener: OnCaelClickListener,
) :
RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnCaelClickListener{
        fun onCaelClick(cael: CaelModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.cael_rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return caelsList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(caelsList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<CaelModel>(itemView){
        val binding = CaelRvItemBinding.bind(itemView)

        override fun bind(item: CaelModel, position: Int) {
            binding.txtNombre.text = item.caelNombre
            binding.txtCasillasTotales.text = "Casillas asignadas: ${item.casillas_asignadas}"
            itemView.setOnClickListener { itemCLickListener.onCaelClick(item, position) }
        }
    }
}