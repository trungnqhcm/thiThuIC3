package com.example.quoctrungnguyen.thithuic3spark

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quoctrungnguyen.thithuic3spark.Common.Common
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.app_bar_question.*
import java.lang.String.format
import java.util.concurrent.TimeUnit

class Result : AppCompatActivity() {

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        Log.d("Checkin","On Result")

//
//        toolbar.title = "RESULT"
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txt_time.text = format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(Common.timer.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(Common.timer.toLong())-TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(Common.timer.toLong())
            ))
        txt_right_answer.text = "${Common.right_answer_count}/${Common.questionList.size}"
        btn_filter_total.text = "${Common.questionList.size}"
        btn_filter_right.text = "${Common.right_answer_count}"
        btn_filter_wrong.text= "${Common.wrong_answer_count}"
        btn_filter_no_answer.text = "${Common.no_answer_count}"

        val percent: Int = Common.right_answer_count*100/Common.questionList.size;
        if(percent > 80)
            txt_result.text = "EXCELLENT"
        else if(percent > 70)
            txt_result.text = "GOOD"
        else if(percent > 60)
            txt_result.text = "FAIR"
        else if(percent > 50)
            txt_result.text = "TRY MORE"
        else if(percent > 40)
            txt_result.text = "POOR"
        else txt_result.text = "FAILING"



    }
}

