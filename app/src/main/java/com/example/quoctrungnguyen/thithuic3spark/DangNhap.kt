package com.example.quoctrungnguyen.thithuic3spark

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.quoctrungnguyen.thithuic3spark.Models.Users
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dang_nhap.*




class DangNhap : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    var user = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)

        Log.d("BUTTON", "Da dc nhan")


        btnSkip.setOnClickListener() {
            dangNhap("trung03@gmail.com", "12345678")
            chuyenSangTrangChonBaiTest()
            Log.d("BUTTON", "Da dc nhan ${user.uid}")
        }

        btnDangNhap.setOnClickListener() {
            val email = edtEmailDangNhap.text.toString()
            val password = editMatKhauDangNhap.text.toString()

            dangNhap(email, password)
        }

         btnChuyenTrangDangKy.setOnClickListener() {
             Log.d("BUTTON", "Da dc nhan ${user.uid}")
            chuyenSangTrangDangKy()
        }
    }

    fun chuyenSangTrangDangKy() {
        val intent = Intent (this,  DangKy::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun dangNhap(email: String, password: String) {
        fbAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid: String = fbAuth.uid.toString()
                    chuyenSangTrangChonBaiTest()
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w("signin", "signInWithEmail:failure", it.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }

    }


    private fun chuyenSangTrangChonBaiTest() {
        val intent = Intent (this,  MenuChonBai::class.java)
        Log.d("BUTTON", "Tao dc Intent")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        //intent.putExtra("uid", user.uid)
        Log.d("Truyen UID", "Da truyen dc ${user.uid}")
        startActivity(intent)
    }

}
