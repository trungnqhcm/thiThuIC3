package com.example.quoctrungnguyen.thithuic3spark.DBHelper

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.quoctrungnguyen.thithuic3spark.Models.Question
import com.example.quoctrungnguyen.thithuic3spark.Interface.MyCallBack
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog

class OnlineDbHelper(internal var context: Context,
                     internal  var firebaseDatabase: FirebaseDatabase  ) {

    internal var edmtQuiz: DatabaseReference;

    init {
        edmtQuiz = this.firebaseDatabase.getReference("EDMTQuiz")

    }

    companion object {
        private var instace: OnlineDbHelper?= null

        @Synchronized
        fun getInstance(context: Context, firebaseDatabase: FirebaseDatabase): OnlineDbHelper {
            if (instace == null)
                instace =
                        OnlineDbHelper(context, firebaseDatabase)
            return  instace!!
        }
    }

    fun readData(myCallBack: MyCallBack, category: String) {
        val dialog: AlertDialog = SpotsDialog.Builder().setContext(context).build()

        if (!dialog.isShowing)
            dialog.show()

        edmtQuiz.child(category)
            .child("question")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(context, ""+p0.message, Toast.LENGTH_SHORT)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val questionList = ArrayList<Question> ()
                    for (questionSnapshot in p0.children) {
                        questionList.add(questionSnapshot.getValue(Question::class.java)!!)
                    }
                    myCallBack.setQuestionList(questionList)

                    if (dialog.isShowing)
                        dialog.dismiss()
                 }

            })

    }

}