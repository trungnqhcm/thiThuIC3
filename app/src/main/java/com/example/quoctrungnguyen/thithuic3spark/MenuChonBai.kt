package com.example.quoctrungnguyen.thithuic3spark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu_chon_bai.*

class MenuChonBai : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_chon_bai)

        btnDangXuat.setOnClickListener() {
            fbAuth.signOut()
            val intent = Intent (this,  DangNhap::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }



        val uid: String = fbAuth.uid.toString()

        val database = FirebaseDatabase.getInstance().reference
        Log.d("FIRE BASE", "get instance dc")

        database.child("user/$uid/username").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val username =p0.getValue(String::class.java)
                tvTenThiSinh.text = username.toString()
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })

        database.child("user/$uid/diemCaoNhat").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val diemCaoNhat =p0.getValue(Int::class.java)
                tvDiemCaoNhat.text = diemCaoNhat.toString()

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })

        database.child("user/$uid/diemGanDay").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val diemGanDay =p0.getValue(Int::class.java)
                tvDiemGanDay.text = diemGanDay.toString()

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })




    }


}
