package com.example.quoctrungnguyen.thithuic3spark.Common

import com.example.quoctrungnguyen.thithuic3spark.Models.Category
import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion
import com.example.quoctrungnguyen.thithuic3spark.Models.Question
import com.example.quoctrungnguyen.thithuic3spark.Models.Users
import com.example.quoctrungnguyen.thithuic3spark.QuestionActivity
import com.example.quoctrungnguyen.thithuic3spark.QuestionFragment

object Common {

    val TOTAL_TIME = 20*60*1000 //20min


    var answerSheetList: MutableList<CurrentQuestion> = ArrayList()

    var questionList: MutableList<Question> = ArrayList()

    var selectedCategory:Category?= null

    var fragmentList: MutableList<QuestionFragment> = ArrayList()

    var selected_values:MutableList<String> = ArrayList()

    var timer = 0

    var user = Users()

    var right_answer_count = 0

    var wrong_answer_count = 0

    enum class ANSWER_TYPE {
        NO_ANSWER,
        RIGH_ANSWER,
        WRONG_ANSER

    }

}