package com.wparja.transitions

import android.animation.AnimatorInflater
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_single_transition_by_xml.*
import kotlinx.android.synthetic.main.activity_single_transition_by_xml.sceneRoot
import kotlinx.android.synthetic.main.activity_single_transition_without_scene.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onClick(view: View) {
        if (view.id == R.id.single_layout_xml) {
            startActivity(Intent(this, SingleTransitionByXmlActivity::class.java))
        } else if (view.id == R.id.single_layout_code) {
            startActivity(Intent(this, SingleTransitionByCodeActivity::class.java))
        } else if (view.id == R.id.single_transition_without_scene) {
            startActivity(Intent(this, SingleTransitionWithoutScene::class.java));
        } else if (view.id == R.id.key_frame_transition) {
            startActivity(Intent(this, KeyFrameActivity::class.java))
        }
    }
}