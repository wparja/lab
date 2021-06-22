package com.wparja.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.activity_single_transition_by_xml.*

class SingleTransitionByXmlActivity : AppCompatActivity() {

    private lateinit var scene1 : Scene
    private lateinit var scene2: Scene
    private lateinit var currentScene: Scene
    private lateinit var transition: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_transition_by_xml)

        // Step 1: Create a Scene object for both the starting and ending layout
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)

        // Step 2: Create a Transition object to define what type of animation you want
        transition  = TransitionInflater.from(this).inflateTransition(R.transition.example_4)


        scene1.enter()
        currentScene = scene1

    }

    fun onClick(view: View) {

        // Step 3: Call TransitionManager.go() to run animation
        currentScene = if (currentScene === scene1) {
            TransitionManager.go(scene2, transition)
            scene2
        } else {
            TransitionManager.go(scene1, transition)
            scene1
        }
    }
}