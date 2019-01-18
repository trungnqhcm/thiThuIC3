package com.example.quoctrungnguyen.thithuic3spark

import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.quoctrungnguyen.thithuic3spark.Adapter.GridAnswerAdapter
import com.example.quoctrungnguyen.thithuic3spark.Adapter.MyFragmentAdapter
import com.example.quoctrungnguyen.thithuic3spark.DBHelper.OnlineDbHelper
import com.example.quoctrungnguyen.thithuic3spark.Common.Common
import com.example.quoctrungnguyen.thithuic3spark.Interface.MyCallBack
import com.example.quoctrungnguyen.thithuic3spark.Models.CurrentQuestion
import com.example.quoctrungnguyen.thithuic3spark.Models.Question
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.app_bar_question.*
import kotlinx.android.synthetic.main.content_question.*
import java.util.concurrent.TimeUnit
import android.util.Log


class QuestionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {



    lateinit var countDownTimer: CountDownTimer

    var time_play = Common.TOTAL_TIME

    lateinit var adapter: GridAnswerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setSupportActionBar(toolbar)

        Log.d("Checkin","Vao Question Activity")
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        genQuestion()

        Log.d("Checkin","After genquestion Questionlist.size = ${Common.questionList.size}")







    }

    private fun getFragmentList() {
        for (i in Common.questionList.indices) {
            val bundle= Bundle()
            bundle.putInt("index", i)
            val fragment = QuestionFragment()
            fragment.arguments = bundle

            Common.fragmentList.add(fragment)
        }
    }

    private fun genItems() {
        Log.d("Checkin","Gen Item Questionlist.size = ${Common.questionList.size}")

        for (i in Common.questionList.indices)
            Common.answerSheetList.add(CurrentQuestion(i, Common.ANSWER_TYPE.NO_ANSWER))
    }

    private fun genQuestion() {
        OnlineDbHelper.getInstance(this, firebaseDatabase = FirebaseDatabase.getInstance())
            .readData(object : MyCallBack {

                override fun setQuestionList(questionList: List<Question>) {
                    Common.questionList.clear()
                    Common.questionList = questionList as MutableList<Question>

                    if (Common.questionList.size == 0) {
                        MaterialStyledDialog.Builder(this@QuestionActivity)
                            .setTitle("Opps!!")
                            .setDescription("Errror")
                            .setPositiveText("OK")
                            .onPositive{dialog, which ->
                                dialog.dismiss()
                                finish()
                            }.show()
                    }else setupQuestion()
                }


            }, Common.selectedCategory!!.name!!.replace(" ","")
                .replace("/", "_"))
    }

    private fun setupQuestion() {
        Log.d("Checkin","Set up question Questionlist.size = ${Common.questionList.size}")


        if (Common.questionList.size > 0) {
            txt_timer.visibility = View.VISIBLE
            txt_right_answer.visibility = View.VISIBLE

            countTimer()

            genItems()

            grid_answer.setHasFixedSize(true)
            if (Common.questionList.size > 0) {
                grid_answer.layoutManager = GridLayoutManager( this, if (Common.questionList.size > 5) Common.questionList.size / 2 else Common.questionList.size)
            }
            adapter = GridAnswerAdapter(this, Common.answerSheetList)
            grid_answer.adapter = adapter

            getFragmentList()

            val fragmentAdapter = MyFragmentAdapter(supportFragmentManager, this, Common.fragmentList)
            view_pager.offscreenPageLimit = Common.questionList.size
            view_pager.adapter = fragmentAdapter

            sliding_tabs.setupWithViewPager(view_pager)
        }
    }

    private fun countTimer() {
        Log.d("Checkin","Vao Count timer")
        countDownTimer = object : CountDownTimer(Common.TOTAL_TIME.toLong(), 1000) {
            override fun onFinish() {
                finishGame()
            }

            override fun onTick(interval: Long) {
                txt_timer.text = (java.lang.String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(interval),
                    TimeUnit.MILLISECONDS.toSeconds(interval) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(interval))))
                time_play -= 1000
            }

        }

    }

    private fun finishGame() {
        //later
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.question, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
