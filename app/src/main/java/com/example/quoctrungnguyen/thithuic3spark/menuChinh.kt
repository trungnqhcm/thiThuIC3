package com.example.quoctrungnguyen.thithuic3spark

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quoctrungnguyen.thithuic3spark.Common.Common
import com.example.quoctrungnguyen.thithuic3spark.Models.Users
import kotlinx.android.synthetic.main.activity_menu_chinh.*
import com.google.firebase.auth.*
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog

class menuChinh : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_chinh)

        Log.d("Activity", "menuChinh")
//
//        val dialog = SpotsDialog.Builder().setContext(this)
//            .setCancelable(false)
//            .build()
//
//        if(!dialog.isShowing)
//            dialog.show()



        btnDangXuat.setOnClickListener() {
            fbAuth.signOut()
            val intent = Intent (this,  DangNhap::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        btnThiThu.setOnClickListener() {
            val intent = Intent (this,  CategoryActivities::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


//        var dialog = SpotsDialog.Builder().setContext(this)
//            .setCancelable(false)
//            .build()
//
//
//        if (!dialog.isShowing)
//            dialog.show()



        val uid: String = fbAuth.uid.toString()

        val database = FirebaseDatabase.getInstance().reference
        Log.d("FIRE BASE", "get instance dc")



        database.child("user/$uid").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user =p0.getValue(Users::class.java)
                tvTenThiSinh.text = user!!.username.toString()
                tvDiemCaoNhat.text = user!!.diemCaoNhat.toString()
                tvDiemGanDay.text = user!!.diemGanDay.toString()

                Common.user = user!!


//                if (dialog.isShowing)
//                    dialog.dismiss()

                Log.d("Truyen data", "truyen ten")

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })



//        database.child("user/$uid/username").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                val username =p0.getValue(String::class.java)
//                tvTenThiSinh.text = username.toString()
//
////                if (dialog.isShowing)
////                    dialog.dismiss()
//
//                Log.d("Truyen data", "truyen ten")
//
//            }
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//        })

//        database.child("EDMTQuiz/Art_Culture/question/1/id").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                Log.w("CheckIn", "onDataChange")
//                val diemCaoNhat =p0.getValue(Int::class.java)
//
//                Log.d("Truyen data", "truyen diem cao nhat")
//
//                tvDiemCaoNhat.text = diemCaoNhat.toString()
//
////                if (dialog.isShowing)
////                    dialog.dismiss()
//
//
//            }
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//        })
//
////        database.child("EDMTQUiz/Test/question/1/id").addValueEventListener(object : ValueEventListener {
////            override fun onDataChange(p0: DataSnapshot) {
////                val diemGanDay =p0.getValue(Int::class.java)
////                tvDiemGanDay.text = diemGanDay.toString()
////
////                Log.d("Truyen data", "truyen diem cao nhat")
////
//////                if (dialog.isShowing)
//////                    dialog.dismiss()
////
////
////
////            }
////            override fun onCancelled(p0: DatabaseError) {
////
////            }
////        })
//
//        database.child("user/$uid/diemGanDay").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                val diemGanDay =p0.getValue(Int::class.java)
//                tvDiemGanDay.text = diemGanDay.toString()
//
//                Log.d("Truyen data", "truyen diem cao nhat")
//
////                if (dialog.isShowing)
////                    dialog.dismiss()
//
//
//
//            }
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//        })
    }
}
