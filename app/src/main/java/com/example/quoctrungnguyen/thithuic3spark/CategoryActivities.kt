package com.example.quoctrungnguyen.thithuic3spark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.example.quoctrungnguyen.thithuic3spark.Common.Common

import com.example.quoctrungnguyen.thithuic3spark.Common.SpacesItemDecoration

//import com.example.quoctrungnguyen.thithuic3spark.DBHelper.OnlineDBHelper
import com.example.quoctrungnguyen.thithuic3spark.Interface.MyCallBack
import com.example.quoctrungnguyen.thithuic3spark.Models.Category
import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion
import com.example.quoctrungnguyen.thithuic3spark.Models.Question
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_category_activities.*

class CategoryActivities : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_activities)

//        recycle_category.setHasFixedSize(true)
//        recycle_category.layoutManager = GridLayoutManager( this,1)
//
//        val adapter = CategoryAdapter( this, MyDatabaseOpenHelper.getInstance(this).allCategories)
//
//
//
//
//        recycle_category.addItemDecoration(SpacesItemDecoration(4))
//        recycle_category.adapter = adapter







        card_test1.setOnClickListener() {
            var Tets1 = Category(1, "Test1", "")
            Common.selectedCategory = Tets1

            val intent = Intent (this,  QuestionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        card_test2.setOnClickListener() {

        }

        card_test2.setOnClickListener() {

        }




    }
}
