package com.example.inspection.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inspection.databinding.InspectionListItemBinding
import com.example.inspection.model.Categories
import com.example.inspection.room.entity.Inspection
import com.example.inspection.view.adapter.InspectionAdapter.OnItemClickListener

class InspectionCategoryAdapter(private var inspectionCategoryList: List<Categories>,private val itemClickListener : OnItemClickListener) :  RecyclerView.Adapter<InspectionCategoryAdapter.InspectionCategoryViewHolder>(){

   inner  class InspectionCategoryViewHolder(private val binding: InspectionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // ViewHolder
        fun bind(category: Categories) {
            binding.tvInspectionArea.text = category.name
            binding.tvInspectionType.visibility = RecyclerView.GONE
        }

        init {
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(inspectionCategoryList[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionCategoryViewHolder {
        // Create new views (invoked by the layout manager)
        val binding = InspectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InspectionCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InspectionCategoryViewHolder, position: Int) {
        // Replace the contents of a view (invoked by the layout manager)
        val item = inspectionCategoryList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        // Return the size of list
        return inspectionCategoryList.size
    }

    fun updateItems(newItems: List<Categories>) {
        inspectionCategoryList = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(category: Categories, position: Int)
    }
}