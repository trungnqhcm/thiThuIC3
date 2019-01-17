package com.example.quoctrungnguyen.thithuic3spark.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quoctrungnguyen.thithuic3spark.Common.Common
import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion
import com.example.quoctrungnguyen.thithuic3spark.R

class GridAnswerAdapter(internal var context: Context,
                        internal var answerSheetList: List<CurrentQuestion>) :
    RecyclerView.Adapter<GridAnswerAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return answerSheetList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (answerSheetList[position].type == Common.ANSWER_TYPE.RIGH_ANSWER)
            holder.question_item.setBackgroundResource(R.drawable.grid_item_right_answer)
        else if (answerSheetList[position].type == Common.ANSWER_TYPE.WRONG_ANSER)
            holder.question_item.setBackgroundResource(R.drawable.grid_item_wrong_answer)
        else
            holder.question_item.setBackgroundResource(R.drawable.grid_item_no_answer)


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.grid_answer_item, p0, false)
        return MyViewHolder(itemView)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var question_item: View

        init {
            question_item = itemView.findViewById(R.id.question_item) as View
        }

    }
}


