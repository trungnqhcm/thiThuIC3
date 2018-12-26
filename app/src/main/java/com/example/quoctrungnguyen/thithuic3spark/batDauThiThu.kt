package com.example.quoctrungnguyen.thithuic3spark

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.quoctrungnguyen.thithuic3spark.R.id.viewpager_main

class batDauThiThu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bat_dau_thi_thu)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        MyPagerAdapter(supportFragmentManager)

        //viewpager_main. = fragmentAdapter
    }
}
