package com.example.inspection.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inspection.R
import com.example.inspection.databinding.FragmentCompletedInspectionBinding
import com.example.inspection.databinding.FragmentDraftInspectionBinding
import com.example.inspection.room.entity.Inspection
import com.example.inspection.view.adapter.InspectionAdapter
import com.example.inspection.viewmodel.InspectionViewModel

class CompletedInspectionFragment : Fragment() {


    private val  inspectionViewModel by lazy {
        InspectionViewModel(this.requireActivity().application)
    }
    private lateinit var binding : FragmentCompletedInspectionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCompletedInspectionBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        inspectionViewModel.getInspectionListFromDB(true)

        binding.recyclerViewCompletedInspection.layoutManager = LinearLayoutManager(this.requireContext())

        val  inspectionList = inspectionViewModel.inspectionListLiveData.value ?: emptyList()

        val inspectionAdapter = InspectionAdapter(inspectionList, object : InspectionAdapter.OnItemClickListener {
            override fun onItemClick(inspection: Inspection) {
                // Handle item click
               /* val intent = Intent(requireContext(), InspectionActivity::class.java)
                intent.putExtra("inspection", inspection)
                startActivity(intent)*/
            }
        })

        binding.recyclerViewCompletedInspection.adapter = inspectionAdapter

        inspectionViewModel.inspectionListLiveData.observe(viewLifecycleOwner) {
            inspectionAdapter.updateItems(it)
            if(it.isEmpty()) {
                binding.tvNoCompletedInspection.visibility = View.VISIBLE
            } else {
                binding.tvNoCompletedInspection.visibility = View.GONE
            }
        }
    }

}