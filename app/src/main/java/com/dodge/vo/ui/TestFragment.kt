package com.dodge.vo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dodge.vo.BaseFragment
import com.dodge.vo.R
import kotlinx.android.synthetic.main.fragment_test.*

/**
 *  Created by linzheng on 2019/9/2.
 */

class TestFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_tag.text = "TEST"

    }


}