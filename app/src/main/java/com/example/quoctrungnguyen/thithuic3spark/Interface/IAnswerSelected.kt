package com.example.quoctrungnguyen.thithuic3spark.Interface

import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion

interface IAnswerSelected {
    fun selectedAnswer(): CurrentQuestion
    fun showCorrectAnswer()
    fun disableAnswer()
    fun resetQuestion()
}