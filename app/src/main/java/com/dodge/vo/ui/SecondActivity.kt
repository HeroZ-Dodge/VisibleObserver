package com.dodge.vo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dodge.vo.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        view_pager.adapter = MyPagerAdapter(supportFragmentManager)
    }


    class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(p0: Int): Fragment {
            return ChildFragment()
        }


        override fun getCount(): Int {
            return 4
        }

    }


}
