package com.example.inspection.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.inspection.databinding.SurveyQuestionItemBinding
import com.example.inspection.model.Questions

class SurveyQuestionAdapter(private var surveyQuestionList: List<Questions>) :  RecyclerView.Adapter<SurveyQuestionAdapter.SurveyQuestionViewHolder>(){

     var selectedAnswerList: HashMap<Int,Int> = hashMapOf()

    inner  class SurveyQuestionViewHolder(val binding: SurveyQuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // ViewHolder
        fun bind(questions:  Questions) {
            binding.tvQuestion.text = questions.name
            binding.tvQuestionNumber.text = "Question: ${(adapterPosition+1).toString()}"
        }

        init {
            binding.root.setOnClickListener {
                //itemClickListener?.onItemClick(inspectionList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyQuestionViewHolder {
        // Create new views (invoked by the layout manager)
        val binding = SurveyQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurveyQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurveyQuestionViewHolder, position: Int) {
        // Replace the contents of a view (invoked by the layout manager)
        val item = surveyQuestionList[position]
        val radioGroup = holder.binding.radioGroup
        // Clear previous radio buttons if any
        radioGroup.removeAllViews()
        // Dynamically create radio buttons based on the number of answer choices
        item.answerChoices.forEach { option ->
            val radioButton = RadioButton(holder.itemView.context).apply {
                text = option.name
                id = option.id!!//View.generateViewId()
                if (item.selectedAnswerChoiceId != null && item.selectedAnswerChoiceId == option.id.toString()) {
                    isChecked = true
                }
            }
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            Log.d("SurveyQuestionAdapter", "Selected answer: ${radioButton.text} id: ${radioButton.id}")
            item.id?.let { selectedAnswerList[it] = radioButton.id }
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        // Return the size of list
        return surveyQuestionList.size
    }

    fun updateItems(newItems: List<Questions>) {
        surveyQuestionList = newItems
        notifyDataSetChanged()
    }

    fun getSelectedAnswers(): HashMap<Int,Int> {
        return selectedAnswerList
    }
}