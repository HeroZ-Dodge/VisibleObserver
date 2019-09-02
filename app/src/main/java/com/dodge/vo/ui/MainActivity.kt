package com.dodge.vo.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dodge.vo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_next.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        val fragment = TestFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_layout, fragment, "Main")
                .commitAllowingStateLoss()
    }
}
