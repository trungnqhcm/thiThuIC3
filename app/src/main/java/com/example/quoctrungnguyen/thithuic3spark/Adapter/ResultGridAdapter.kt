package com.example.quoctrungnguyen.thithuic3spark.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion
import com.example.quoctrungnguyen.thithuic3spark.R
import com.example.quoctrungnguyen.thithuic3spark.Common.Common
import kotlinx.android.synthetic.main.layout_result_item.view.*

class ResultGridAdapter(internal var context: Context,
                        internal var answerSheetList:List<CurrentQuestion>): RecyclerView.Adapter< ResultGridAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_result_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return answerSheetList.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.btn_question_num.text =StringBuilder("Question").append(answerSheetList[position].questionIndex+1)
        if (answerSheetList[position].type  == Common.ANSWER_TYPE.RIGH_ANSWER)
        {
            myViewHolder.btn_question_num.setBackgroundResource(R.drawable.grid_item_right_answer)
            val img  = context.resources.getDrawable(R.drawable.ic_check_black_24dp)
            myViewHolder.btn_question_num.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,img)

        }
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var btn_question_num : Button
        init{
            btn_question_num =  itemView.findViewById(R.id.btn_question) as Button
            btn_question_num.setOnClickListener {
                val sendBroadcast = LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_BACK_FROM_RESULT)
                        .putExtra(Common.KEY_BACK_FROM_RESULT,answerSheetList[adapterPosition].questionIndex))
            }
        }

    }

}