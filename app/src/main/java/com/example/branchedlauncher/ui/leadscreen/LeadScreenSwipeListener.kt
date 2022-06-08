package com.example.branchedlauncher.ui.leadscreen

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.navigation.NavController
import com.example.branchedlauncher.R
import kotlin.math.abs

/**
 * Swipe listener and navigation logic for LeadScreenFragment.
 **/

class LeadScreenSwipeListener(
    context: Context?,
    private val navController: NavController
) : View.OnTouchListener {
    private val gestureDetector: GestureDetector

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (
                    abs(diffX) > Companion.SWIPE_THRESHOLD
                    && abs(velocityX) > Companion.SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                }
            } else if (
                abs(diffY) > Companion.SWIPE_THRESHOLD
                && abs(velocityY) > Companion.SWIPE_VELOCITY_THRESHOLD
            ) {
                if (diffY > 0) {
                    onSwipeBottom()
                } else {
                    onSwipeTop()
                }
            }
            return true
        }


    }

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    companion object {
        private const val SWIPE_VELOCITY_THRESHOLD = 100
        private const val SWIPE_THRESHOLD = 100
    }

    fun onSwipeRight() {
        Log.d("onb", "on_swipe_right")
    }

    fun onSwipeLeft() {
        Log.d("onb", "on_swipe_left")
    }

    fun onSwipeTop() {
        Log.d("onb", "on_swipe_top")
    }

    fun onSwipeBottom() {
        Log.d("onb", "on_swipe_bottom")
        navController.navigate(R.id.action_LeadFragment_to_SearchFragment)

    }


}

