package com.example.inspection.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inspection.databinding.InspectionListItemBinding
import com.example.inspection.room.entity.Inspection

class InspectionAdapter(private var inspectionList: List<Inspection>) : RecyclerView.Adapter<InspectionAdapter.InspectionViewHolder>(){

    class InspectionViewHolder(private val binding: InspectionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // ViewHolder
        fun bind(inspection: Inspection) {
            binding.tvInspectionArea.text = inspection.area?.name
            binding.tvInspectionType.text = inspection.inspectionType?.name
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
}