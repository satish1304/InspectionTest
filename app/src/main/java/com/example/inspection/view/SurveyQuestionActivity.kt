package com.example.inspection.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inspection.databinding.ActivityInspectionSurveyQuestionBinding
import com.example.inspection.model.Categories
import com.example.inspection.view.adapter.SurveyQuestionAdapter

class SurveyQuestionActivity  : AppCompatActivity() {

    private var score: Double = 0.0
    private lateinit var binding : ActivityInspectionSurveyQuestionBinding
    private lateinit var category: Categories

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityInspectionSurveyQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Survey Questions"
        val bundle = intent.extras
        category = bundle?.getParcelable<Categories>("category") as Categories
        binding.tvCategory.text = category.name
        binding.recyclerViewSurveyQuestions.layoutManager = LinearLayoutManager(this)
        val surveyQuestionAdapter = SurveyQuestionAdapter(category.questions)
        binding.recyclerViewSurveyQuestions.adapter = surveyQuestionAdapter
        binding.btnSaveSurvey.setOnClickListener {
            if(surveyQuestionAdapter.selectedAnswerList.isEmpty()){
                // Show error message
                Toast.makeText(this, "Please select an answer for each question", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(surveyQuestionAdapter.selectedAnswerList.size != category.questions.size){
                // Show error message
                Toast.makeText(this, "Please select an answer for each question", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            category.questions.forEach { question ->
                if(question.selectedAnswerChoiceId == null){
                    val selectedAns = surveyQuestionAdapter.selectedAnswerList[question.id]
                    question.selectedAnswerChoiceId = selectedAns.toString()
                }
            }
            setResult(RESULT_OK, intent.putExtra("category", category))
            finish()
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