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
import com.example.quoctrungnguyen.thithuic3spark.Models.Users
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_category_activities.*
import kotlinx.android.synthetic.main.activity_menu_chinh.*

class CategoryActivities : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    val uid: String = fbAuth.uid.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_activities)

        if (uid != "")
            getData(uid)


        btn_dang_xuat.setOnClickListener() {
            fbAuth.signOut()
            Common.user = Users()

            val intent = Intent (this@CategoryActivities,  DangNhap::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        card_test1.setOnClickListener() {
            val Tets1 = Category(1, "Test1", "")
            Common.selectedCategory = Tets1

            val intent = Intent (this@CategoryActivities,  QuestionActivity::class.java)
            startActivity(intent)
        }

        card_test2.setOnClickListener() {
            val Tets2 = Category(2, "Test2", "")
            Common.selectedCategory = Tets2

            val intent = Intent (this@CategoryActivities,  QuestionActivity::class.java)
            startActivity(intent)

        }

        card_test2.setOnClickListener() {
            val Tets3 = Category(3, "Test3", "")
            Common.selectedCategory = Tets3

            val intent = Intent (this@CategoryActivities,  QuestionActivity::class.java)
            startActivity(intent)

        }

    }

    fun getData(uid: String) {
        val database = FirebaseDatabase.getInstance().reference
        database.child("user/$uid").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user =p0.getValue(Users::class.java)

                Common.user = user!!

                txt_ten_thi_sinh.text = Common.user.username



                Log.d("Truyen data", "truyen ten")

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}
