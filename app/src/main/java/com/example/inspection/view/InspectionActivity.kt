package com.example.inspection.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inspection.databinding.ActivityInspectionBinding
import com.example.inspection.model.Categories
import com.example.inspection.model.Survey
import com.example.inspection.network.InspectionSubmitRequest
import com.example.inspection.room.entity.Inspection
import com.example.inspection.utils.AppUtils
import com.example.inspection.view.adapter.InspectionCategoryAdapter
import com.example.inspection.viewmodel.InspectionViewModel

class InspectionActivity : AppCompatActivity(){

    private lateinit var binding : ActivityInspectionBinding
    private lateinit var inspection: Inspection
    private lateinit var categoryList : ArrayList<Categories>
    private  var selectedCategoryPosition: Int = 0
    private var submittedCategoryCount = 0 // to keep track of how many categories have been submitted
    private val  inspectionViewModel by lazy {
        InspectionViewModel(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInspectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Inspection Details"
        val bundle = intent.extras
        inspection = bundle?.getParcelable<Inspection>("inspection") as Inspection
        binding.tvInspectionArea.text = inspection.area?.name
        binding.tvInspectionType.text = inspection.inspectionType?.name
        binding.recyclerViewInspectionCategory.layoutManager = LinearLayoutManager(this)
        categoryList = inspection.survey?.categories as ArrayList<Categories>
        val inspectionCategoryAdapter = InspectionCategoryAdapter(categoryList, object : InspectionCategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: Categories, position: Int) {
                // Handle item click
                selectedCategoryPosition = position
                val intent = Intent(this@InspectionActivity, SurveyQuestionActivity::class.java)
                intent.putExtra("category", category)
                startActivityForResult(intent,502)

            }
        })
        binding.recyclerViewInspectionCategory.adapter = inspectionCategoryAdapter

        binding.btnSubmitInspection.setOnClickListener {

            if(!AppUtils.isNetworkAvailable(this)){
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            inspection.survey = Survey(categoryList)
            val submitRequest = InspectionSubmitRequest(inspection)
            inspectionViewModel.submitInspection(submitRequest)
            inspectionViewModel.submitInspectionResponseCd.observe(this) {
                if (it == 200) {
                    // Show success message
                    Toast.makeText(
                        this@InspectionActivity,
                        "Inspection submitted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    inspectionViewModel.updateInspectionCompleted(
                        inspection.id,
                        AppUtils.getCurrentDateTime()
                    )
                    finish()
                } else {
                    // Show error message
                    Toast.makeText(this@InspectionActivity,
                        "Failed to submit inspection",
                        Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 502){
            submittedCategoryCount++
            val category = data?.getParcelableExtra<Categories>("category")
            categoryList[selectedCategoryPosition] = category!!
            val survey = Survey(categoryList)
            inspection.id.let { inspectionViewModel.updateInspectionCategory(survey,inspectionViewModel.calculateScore(category), it) }
            if(submittedCategoryCount == categoryList.size){
                binding.btnSubmitInspection.visibility = android.view.View.VISIBLE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button click (e.g., navigate up or finish the activity)
                onBackPressed()
                return true
            }
            // Other menu items (if any)
            else -> return super.onOptionsItemSelected(item)
        }
    }
}