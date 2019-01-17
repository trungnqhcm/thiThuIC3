package com.example.quoctrungnguyen.thithuic3spark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dang_ky.*
import com.google.firebase.auth.FirebaseAuth
import android.support.design.widget.Snackbar
import android.view.View
import com.example.quoctrungnguyen.thithuic3spark.Models.Users
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.FirebaseDatabase



class DangKy : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    var fbDatabase = FirebaseDatabase.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)

        btnChuyenTrangDangNhap.setOnClickListener() {
            chuyenSangTrangDangNhap()
        }

        btnDangKy.setOnClickListener() {
            val user= Users(edtTenThiSinh.text.toString(), edtEmailMoi.text.toString(), edtMatKhauMoi.text.toString())

            if (user.username.isEmpty() || user.email.isEmpty() || user.password.isEmpty()) {
                showSnackBar(it, "Vui lòng điền đầy đủ thông tin ở ")
                return@setOnClickListener
            } else {

                dangKyTaiKhoanMoi(it, user)
            }
        }
    }

    private fun dangKyTaiKhoanMoi(view: View, user: Users) {
        val email = user.email
        val password = user.password
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                //Tao tai khoan

                val uid = fbAuth.uid.toString()
                user.uid = uid
                val refUser = fbDatabase.getReference("user/$uid")
                refUser.setValue(user)

                var intent = Intent(this, DangNhap::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            }else{
                showSnackBar(view,"Error: ${task.exception?.message}")
            }
        })

    }

//    private fun taoTaiKhoan(user: Users) {
//        val uid = fbAuth.uid.toString()
//        user.uid = uid
//        val refUser = fbDatabase.getReference("user/$uid")
//        refUser.setValue(user)
//            .addOnCompleteListener {
//                Toast.makeText(this, "Đăng ký Thành công", Toast.LENGTH_SHORT).show()
//                chuyenSangTrangDangNhap()
//            }
//    }

    private fun chuyenSangTrangDangNhap() {
        val intent = Intent (this,  DangNhap::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showSnackBar(it: View, text: String) {
        val snack = Snackbar.make(it,text,Snackbar.LENGTH_LONG)
        snack.show()
    }
}
