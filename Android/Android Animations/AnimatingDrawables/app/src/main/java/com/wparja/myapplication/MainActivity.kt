package com.wparja.myapplication

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var batteryAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isChecked = true

        animatedVectorDrawable.setOnClickListener {
            if (isChecked) {
                checkToClose()
            } else {
                closeToClose()
            }
            isChecked = !isChecked
        }

    }

    private fun closeToClose() {
        animatedVectorDrawable.setImageResource(R.drawable.avd_reverse_anim)
        val avdCloseToCheck = animatedVectorDrawable.drawable as AnimatedVectorDrawable
        avdCloseToCheck.start()
    }

    private fun checkToClose() {
        animatedVectorDrawable.setImageResource(R.drawable.avd_anim)
        val avdCheckToClose = animatedVectorDrawable.drawable as AnimatedVectorDrawable
        avdCheckToClose.start()
    }

    override fun onStart() {
        super.onStart()
        imageView.setBackgroundResource(R.drawable.battery_animation_list)
        batteryAnimation = imageView.background as AnimationDrawable
        batteryAnimation.start()
    }

    fun startAnimation(view: View) {
        if (batteryAnimation.isRunning) {
            batteryAnimation.stop()
        } else {
            batteryAnimation.start()
        }
    }
}