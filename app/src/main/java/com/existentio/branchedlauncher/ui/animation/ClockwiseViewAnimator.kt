package com.existentio.branchedlauncher.ui.animation

import android.animation.ObjectAnimator
import android.graphics.Path
import android.view.View
import androidx.core.animation.doOnRepeat

class ClockwiseViewAnimator : ViewAnimator {

     override fun constructAnimation(
        view: View,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float
    ): ObjectAnimator {

        val path = Path().apply {
            arcTo(
                left,
                top,
                right,
                bottom,
                startAngle,
                sweepAngle,
                true
            )
        }

        return ObjectAnimator.ofFloat(
            view,
            View.X,
            View.Y,
            path
        )
    }



    override fun startAnimation(
        animator: ObjectAnimator,
        animDuration: Long,
        repeatCount: Int
    ) {
        animator.apply {
            duration = animDuration
            start()
        }

        animator.repeatCount = repeatCount
        animator.doOnRepeat {
            //temporally do nothing
        }
    }


}