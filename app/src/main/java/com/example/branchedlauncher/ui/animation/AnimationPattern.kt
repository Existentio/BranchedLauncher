package com.example.branchedlauncher.ui.animation

class AnimationPattern {

    fun defineAnimationPattern(
        animator: ViewAnimator,
        elemsCount: Int
    ): MutableList<Map<String, Float>> {
        when (animator) {
            is ClockwiseViewAnimator ->
                return (ClockwiseAnimationPattern().performAnimation(elemsCount))

        }
        return stub()
    }


    private fun stub(): MutableList<Map<String, Float>> {
        return mutableListOf()
    }


}

