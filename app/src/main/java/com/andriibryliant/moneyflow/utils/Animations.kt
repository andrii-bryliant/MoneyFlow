package com.andriibryliant.moneyflow.utils

import android.view.animation.AlphaAnimation

class Animations {
    companion object{
        fun buttonShowAnimation(duration: Long): AlphaAnimation {
            val showAnimation = AlphaAnimation(0.2F, 1F)
            showAnimation.duration = duration
            showAnimation.fillAfter = true
            return showAnimation
        }
    }
}