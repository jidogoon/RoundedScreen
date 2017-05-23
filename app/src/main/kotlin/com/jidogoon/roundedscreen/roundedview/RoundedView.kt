package com.jidogoon.roundedscreen.roundedview

import android.content.Context
import android.graphics.PixelFormat
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.utils.PreferenceUtils

/**
 * Created by dohyunji on 2017. 5. 22..
 */
class RoundedView() {
    var context: Context? = null
    var roundedView: View? = null

    constructor(context: Context) : this() {
        this.context = context
        setContentView(getOptions())
    }

    constructor(context: Context, options: RoundedViewOptions) : this() {
        this.context = context
        setContentView(options)
    }

    private fun getOptions(): RoundedViewOptions {
        val options = RoundedViewOptions()
        options.topLeft = PreferenceUtils.getAppPreferenceBoolean(context!!,
                PreferenceUtils.PREF_NAME_ROUNDEDVIEW,
                PreferenceUtils.PREF_KEY_ROUNDEDVIEW_TOPLEFT, options.topLeft)
        options.topRight = PreferenceUtils.getAppPreferenceBoolean(context!!,
                PreferenceUtils.PREF_NAME_ROUNDEDVIEW,
                PreferenceUtils.PREF_KEY_ROUNDEDVIEW_TOPRIGHT, options.topRight)
        options.bottomLeft = PreferenceUtils.getAppPreferenceBoolean(context!!,
                PreferenceUtils.PREF_NAME_ROUNDEDVIEW,
                PreferenceUtils.PREF_KEY_ROUNDEDVIEW_BOTTOMLEFT, options.bottomLeft)
        options.bottomRight = PreferenceUtils.getAppPreferenceBoolean(context!!,
                PreferenceUtils.PREF_NAME_ROUNDEDVIEW,
                PreferenceUtils.PREF_KEY_ROUNDEDVIEW_BOTTOMRIGHT, options.bottomRight)
        options.size = PreferenceUtils.getAppPreferenceInt(context!!,
                PreferenceUtils.PREF_NAME_ROUNDEDVIEW,
                PreferenceUtils.PREF_KEY_ROUNDEDVIEW_SIZE, options.size)
        return options
    }

    private fun setContentView(options: RoundedViewOptions) {
        val inflater = LayoutInflater.from(context)
        roundedView = inflater.inflate(R.layout.roundedview, null)
        val vTopLeft = roundedView?.findViewById(R.id.vTopLeft)
        val vTopRight = roundedView?.findViewById(R.id.vTopRight)
        val vBottomLeft = roundedView?.findViewById(R.id.vBottomLeft)
        val vBottomRight = roundedView?.findViewById(R.id.vBottomRight)

        vTopLeft?.visibility = if (options.topLeft) View.VISIBLE else View.GONE
        vTopRight?.visibility = if (options.topRight) View.VISIBLE else View.GONE
        vBottomLeft?.visibility = if (options.bottomLeft) View.VISIBLE else View.GONE
        vBottomRight?.visibility = if (options.bottomRight) View.VISIBLE else View.GONE

        val lpTopLeft = vTopLeft?.layoutParams as RelativeLayout.LayoutParams
        val lpTopRight = vTopRight?.layoutParams as RelativeLayout.LayoutParams
        val lpBottomLeft = vBottomLeft?.layoutParams as RelativeLayout.LayoutParams
        val lpBottomRight = vBottomRight?.layoutParams as RelativeLayout.LayoutParams
        lpTopLeft.width = options.size
        lpTopLeft.height = options.size
        lpTopRight.width = options.size
        lpTopRight.height = options.size
        lpBottomLeft.width = options.size
        lpBottomLeft.height = options.size
        lpBottomRight.width = options.size
        lpBottomRight.height = options.size
        vTopLeft.layoutParams = lpTopLeft
        vTopRight.layoutParams = lpTopRight
        vBottomLeft.layoutParams = lpBottomLeft
        vBottomRight.layoutParams = lpBottomRight

        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT)
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.addView(roundedView, params)
    }

    fun release() {
        if (roundedView != null) {
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.removeView(roundedView)
            roundedView?.destroyDrawingCache()
            roundedView = null
            System.gc()
        }
    }
}