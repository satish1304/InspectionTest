package com.example.inspection.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inspection.R
import com.example.inspection.databinding.FragmentDraftInspectionBinding
import com.example.inspection.room.entity.Inspection
import com.example.inspection.utils.AppUtils
import com.example.inspection.view.adapter.InspectionAdapter
import com.example.inspection.viewmodel.InspectionViewModel

class DraftInspectionFragment : Fragment() {

    private val  inspectionViewModel by lazy {
        InspectionViewModel(this.requireActivity().application)
    }

    private lateinit var binding : FragmentDraftInspectionBinding
    private var inspectionList = listOf<Inspection>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDraftInspectionBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        inspectionViewModel.getInspectionListFromDB(false)

        binding.btnStartInspection.setOnClickListener {
            if(AppUtils.isNetworkAvailable(this.requireContext())){
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            inspectionViewModel.startInspection()
        }

        binding.recyclerViewDraftInspection.layoutManager = LinearLayoutManager(this.requireContext())

         inspectionList = inspectionViewModel.inspectionListLiveData.value ?: emptyList()

        val inspectionAdapter = InspectionAdapter(inspectionList, object : InspectionAdapter.OnItemClickListener {
            override fun onItemClick(inspection: Inspection) {
                // Handle item click
                val intent = Intent(requireContext(), InspectionActivity::class.java)
                intent.putExtra("inspection", inspection)
                startActivity(intent)
            }
        })

        binding.recyclerViewDraftInspection.adapter = inspectionAdapter

        inspectionViewModel.inspectionListLiveData.observe(viewLifecycleOwner) {
            inspectionAdapter.updateItems(it)
            if (it.isEmpty()) {
                binding.tvNoDraftInspection.visibility = View.VISIBLE
            } else {
                binding.tvNoDraftInspection.visibility = View.GONE
            }
        }
    }
}