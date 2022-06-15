package com.existentio.branchedlauncher.ui.animation.patterns

class ClockwiseAnimationPattern {
     fun performAnimation(elemsCount: Int): MutableList<Map<String, Float>> {
        val clockwisePattern = mutableListOf<Map<String, Float>>()
        val shiftAngle: (Int) -> Float = { it * 60f }

        for (i in 0 until elemsCount) {
            clockwisePattern.add(
                mapOf(
                    "left" to 100f,
                    "top" to 400f,
                    "right" to 700f,
                    "bottom" to 1100f,
                    "startAngle" to shiftAngle(i),
                    "sweepAngle" to 359.9f
                )
            )
        }
        return clockwisePattern
    }
}