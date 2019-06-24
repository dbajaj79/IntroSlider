package com.example.introslider.ui.kotlincode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.R
import com.example.databinding.ActivitySliderBinding
import com.example.introslider.MainActivity

class SliderActivity : AppCompatActivity() {

    private lateinit var  activitySliderBinding:ActivitySliderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySliderBinding=DataBindingUtil.setContentView(this,R.layout.activity_slider)
        val myPagerAdapter = MyPagerAdapter(getScreens())
        activitySliderBinding.adapter = myPagerAdapter
        activitySliderBinding.onclick = MyButtonClick()
    }
    fun getScreens():Array<Int> = arrayOf(R.layout.intro_screen1,R.layout.intro_screen2,R.layout.intro_screen3)

    inner  class MyButtonClick: View.OnClickListener
    {
        override fun onClick(p0: View?) {

            when(p0!!.id)
            {
                R.id.btn_back-> onBack()

                R.id.btn_next-> onNext()
            }
        }

    }


    fun onBack()
    {
        val i = getItemForBack(1)
        if(i>=0)
        {
            activitySliderBinding.viewPagerIntro.currentItem=i
        }
        else
        {
            println("Nothing to Move Backward")
        }
    }
fun onNext()
{
    val i = getItem(+1)
    if(i<getScreens().size)
    {
        activitySliderBinding.viewPagerIntro.currentItem = i
    }
    else
    {
        launchMainActivity()
    }

}
    fun launchMainActivity()
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun getItem(i:Int):Int
    {

       return activitySliderBinding.viewPagerIntro.currentItem+i
    }
    fun getItemForBack(i:Int):Int
    {
        return activitySliderBinding.viewPagerIntro.currentItem-i
    }



}
