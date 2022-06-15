package com.existentio.branchedlauncher.ui.animation

import android.animation.ObjectAnimator
import android.view.View

interface ViewAnimator {
    fun constructAnimation(
        view: View,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float
    ): ObjectAnimator {
        return ObjectAnimator()
    }


    fun startAnimation(
        animator: ObjectAnimator,
        animDuration: Long,
        repeatCount: Int = 0
    )

}



