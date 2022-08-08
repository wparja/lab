package com.wparja.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_single_transition_by_xml.*
import kotlinx.android.synthetic.main.activity_single_transition_by_xml.sceneRoot
import kotlinx.android.synthetic.main.activity_single_transition_without_scene.*

class SingleTransitionWithoutScene : AppCompatActivity() {

    private var visibility: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_transition_without_scene)
    }


    fun fadeAnimation(view: View) {

        val transition = Fade()

        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        txtDescription.visibility = if (visibility) View.INVISIBLE else View.VISIBLE
        visibility = !visibility

    }

    fun slideAnimation(view: View) {

        val transition = Slide(Gravity.END)

        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        txtDescription.visibility = if (visibility) View.INVISIBLE else View.VISIBLE
        visibility = !visibility

    }
}