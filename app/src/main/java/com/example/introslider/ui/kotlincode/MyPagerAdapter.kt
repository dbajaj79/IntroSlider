package com.example.introslider.ui.kotlincode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class MyPagerAdapter(screens: Array<Int>) : PagerAdapter() {

     var screens: Array<Int>

    init {
        this.screens = screens
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(screens[position],container,false)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if(`object` is View)
        {
            container.removeView(`object`)
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return screens.size
    }

}