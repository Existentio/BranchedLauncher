package com.existentio.branchedlauncher.ui.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class AppView(
    context: Context
) : View(context) {

    fun attachViewsToAppLayout(
        layout: LinearLayout,
        vararg childView: View
    ): LinearLayout {
        childView.forEachIndexed { _, value -> layout.addView(value) }
        return layout
    }

    fun createAppLayout(): LinearLayout = LinearLayout(context)

    fun createAppIcon(appIconResourceId: Int): ImageView {
        val imageView = ImageView(context)
        imageView.setImageResource(appIconResourceId)
        return imageView
    }

    fun createAppIcon(appIconResourceId: Drawable): ImageView {
        val imageView = ImageView(context)
        imageView.setImageDrawable(appIconResourceId)
        return imageView
    }

    fun createAppName(appName: String): TextView {
        val textView = TextView(context)
        textView.text = appName
        return textView
    }

    object AppViewConstants {

        const val APP_LAYOUT_WIDTH = 300
        const val APP_LAYOUT_HEIGHT = 300

        const val APP_ICON_WIDTH_SMALL = 96
        const val APP_ICON_HEIGHT_SMALL = 96

        const val APP_ICON_WIDTH_MEDIUM = 144
        const val APP_ICON_HEIGHT_MEDIUM = 144

        const val APP_ICON_WIDTH_LARGE = 240
        const val APP_ICON_HEIGHT_LARGE = 240
    }


}
