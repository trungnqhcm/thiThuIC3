package com.example.quoctrungnguyen.thithuic3spark
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    var uid : String = ""
    var diemGanvaCaobBaiThi1: IntArray = intArrayOf(0,0)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getIntArray("diemGanvaCao")?.let {
            diemGanvaCaobBaiThi1 = it
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_first, container, false)
        view.btnThiThu.setOnClickListener() {
            val intent = Intent (context,  GamePlay::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        return view

    }



}
