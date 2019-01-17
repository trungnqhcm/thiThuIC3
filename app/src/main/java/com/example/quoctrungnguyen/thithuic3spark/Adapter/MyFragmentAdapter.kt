package com.example.quoctrungnguyen.thithuic3spark.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.quoctrungnguyen.thithuic3spark.QuestionFragment
import java.lang.StringBuilder

class MyFragmentAdapter (fm: FragmentManager, var context: Context,
                         var fragmenList: List<QuestionFragment>): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return fragmenList[p0]
    }

    override fun getCount(): Int {
        return fragmenList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return StringBuilder("Câu ").append(position+1).toString()
    }

    internal var instance: MyFragmentAdapter?= null

}