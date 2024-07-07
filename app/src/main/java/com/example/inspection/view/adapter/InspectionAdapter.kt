package com.example.inspection.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inspection.databinding.InspectionListItemBinding
import com.example.inspection.room.entity.Inspection

class InspectionAdapter(private var inspectionList: List<Inspection>,private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<InspectionAdapter.InspectionViewHolder>(){


   inner class InspectionViewHolder(private val binding: InspectionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // ViewHolder
        fun bind(inspection: Inspection) {
            binding.tvInspectionArea.text = inspection.area.name
            binding.tvInspectionType.text = inspection.inspectionType.name
            if(inspection.completed) {
                binding.tvInspectionScore.text = "Score : ${inspection.score.toString()}"
                binding.tvInspectionScore.visibility = RecyclerView.VISIBLE
                binding.tvInspectionDate.visibility = RecyclerView.VISIBLE
                binding.tvInspectionDate.text = "Completed on : ${inspection.inspectionDate}"
            }else{
                binding.tvInspectionScore.visibility = RecyclerView.GONE
                binding.tvInspectionDate.visibility = RecyclerView.GONE
            }
        }

        init {
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(inspectionList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionViewHolder {
        // Create new views (invoked by the layout manager)
       val binding = InspectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InspectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InspectionViewHolder, position: Int) {
        // Replace the contents of a view (invoked by the layout manager)
        val item = inspectionList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        // Return the size of list
        return inspectionList.size
    }

    fun updateItems(newItems: List<Inspection>) {
        inspectionList = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(inspection: Inspection)
    }


}