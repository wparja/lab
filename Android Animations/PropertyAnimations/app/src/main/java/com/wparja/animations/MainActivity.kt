package com.wparja.animations

import android.animation.*
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun rotateAnimation(view: View) {
        val rotateAnimator = AnimatorInflater.loadAnimator(this, R.animator.rotate)
        rotateAnimator?.apply {
            setTarget(imageView)
            start()
        }
    }
    fun scaleAnimation(view: View) {
        val scaleAnimator = AnimatorInflater.loadAnimator(this, R.animator.scale)
        scaleAnimator?.apply {
            setTarget(imageView)
            start()
        }
    }
    fun translateAnimation(view: View) {
        val translateAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        translateAnimator?.apply {
            setTarget(imageView)
            start()
        }
    }
    fun fadeAnimation(view: View) {
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        fadeAnimator?.apply {
            setTarget(imageView)
            start()
        }
    }

    fun rotateByCode(view: View) {
        val rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -180f);
        rotate.apply {
            duration = 1000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            start()
        }

    }
    fun scaleByCode(view: View) {
        val translate = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f);
        translate.apply {
            duration = 1000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            start()
        }
    }
    fun translateByCode(view: View) {
        val translate = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
        translate.apply {
            duration = 1000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            start()
        }
    }
    fun fadeByCode(view: View) {
        val fade = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f)
        fade.apply {
            duration = 1500
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            start()
        }
    }

    fun setByXml(view: View) {
        var scale = AnimatorInflater.loadAnimator(this, R.animator.set_animator);
        scale?.apply {
            setTarget(imageView)
            start()
        }
    }
    fun setByCode(view: View) {

        val rootSet = AnimatorSet()

        val flip = ObjectAnimator.ofFloat(imageView, "rotationX", 0.0f, 360.0f);
        flip.duration = 500

        val childSet = AnimatorSet()

        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.5f);
        scaleX.duration = 500

        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.5f);
        scaleY.duration = 500

        rootSet.playSequentially(flip, childSet)
        childSet.playTogether(scaleX, scaleY)

        rootSet.start()

    }

    fun interpolatorByXml(view: View) {
        var interpolator = AnimatorInflater.loadAnimator(this, R.animator.set_animator_interpolator)
        interpolator.apply {
            setTarget(imageView)
            start()
        }
    }

    fun interpolatorByCode(view: View) {

        val rootSet = AnimatorSet()

        val flip = ObjectAnimator.ofFloat(imageView, "rotationX", 0.0f, 360.0f)
        flip.duration = 500

        val childSet = AnimatorSet()

        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2.5f)
        scaleX.duration = 1000
        scaleX.interpolator = BounceInterpolator()

        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2.5f)
        scaleY.duration = 1000
        scaleY.interpolator = OvershootInterpolator()

        childSet.playTogether(scaleX, scaleY)
        rootSet.playSequentially(flip, childSet)

        rootSet.start()
    }

    fun viewPropertyAnimatorByXml(view: View) {
        val vpa = imageView.animate()
        vpa.apply {
            duration = 1000
            rotationX(360f)
            scaleX(1.5f)
            scaleY(1.5f)
            interpolator = OvershootInterpolator()
            translationX(200f)
            alpha(0.5f)
            start()
        }


    }
    fun propertyValuesHolder(view: View) {

        val rotX = PropertyValuesHolder.ofFloat("rotationX", 360f)
        val scaX = PropertyValuesHolder.ofFloat("scaleX", 1.5f)
        val scaY = PropertyValuesHolder.ofFloat("scaleY", 1.5f)

        val objA = ObjectAnimator.ofPropertyValuesHolder(imageView, rotX, scaX, scaY)

        objA.apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            start()
        }
    }
}