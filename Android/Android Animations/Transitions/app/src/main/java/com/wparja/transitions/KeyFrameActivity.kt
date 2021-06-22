package com.wparja.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.key_frame.*

class KeyFrameActivity : AppCompatActivity() {
    private var isDetailLayout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.key_frame)

        constraintLayout.setOnClickListener {
            if (isDetailLayout) {
                swapFrames(R.layout.key_frame)
            } else {
                swapFrames(R.layout.key_frame_detail)
            }
        }
    }


    private fun swapFrames(layoutId: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, layoutId)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateInterpolator(1f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraintLayout, transition)

        constraintSet.applyTo(constraintLayout)

        isDetailLayout = !isDetailLayout
    }
}