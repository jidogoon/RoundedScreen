package com.jidogoon.roundedscreen.roundedview

import android.content.Context
import android.graphics.PixelFormat
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.utils.PreferenceUtils

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class RoundedView() {
    private lateinit var context: Context
    private var roundedView: View? = null

    constructor(context: Context) : this() {
        this.context = context
        setContentView()
        invalidate(getOptions())
    }

    constructor(context: Context, options: RoundedViewOptions) : this() {
        this.context = context
        setContentView()
        invalidate(options)
    }

    private fun getOptions(): RoundedViewOptions {
        val ctx = context
        val options = RoundedViewOptions()
        options.topLeft = PreferenceUtils.getSharedPreferenceBoolean(ctx, getString(R.string.pref_key_round_top_left), options.topLeft)
        options.topRight = PreferenceUtils.getSharedPreferenceBoolean(ctx, getString(R.string.pref_key_round_top_right), options.topRight)
        options.bottomLeft = PreferenceUtils.getSharedPreferenceBoolean(ctx, getString(R.string.pref_key_round_bottom_left), options.bottomLeft)
        options.bottomRight = PreferenceUtils.getSharedPreferenceBoolean(ctx, getString(R.string.pref_key_round_bottom_right), options.bottomRight)
        options.size = PreferenceUtils.getSharedPreferenceInt(ctx, getString(R.string.pref_key_round_size), options.size)
        return options
    }

    private fun getString(@StringRes resId: Int): String = context.getString(resId)

    private fun setContentView() {
        val inflater = LayoutInflater.from(context)
        roundedView = inflater.inflate(R.layout.roundedview, null)
        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                PixelFormat.TRANSLUCENT)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.addView(roundedView, params)
    }

    fun invalidate(options: RoundedViewOptions) {
        val vTopLeft = roundedView!!.findViewById<View>(R.id.vTopLeft)
        val vTopRight = roundedView!!.findViewById<View>(R.id.vTopRight)
        val vBottomLeft = roundedView!!.findViewById<View>(R.id.vBottomLeft)
        val vBottomRight = roundedView!!.findViewById<View>(R.id.vBottomRight)

        vTopLeft.visibility = if (options.topLeft) View.VISIBLE else View.GONE
        vTopRight.visibility = if (options.topRight) View.VISIBLE else View.GONE
        vBottomLeft.visibility = if (options.bottomLeft) View.VISIBLE else View.GONE
        vBottomRight.visibility = if (options.bottomRight) View.VISIBLE else View.GONE

        val lpTopLeft = vTopLeft.layoutParams as RelativeLayout.LayoutParams
        val lpTopRight = vTopRight.layoutParams as RelativeLayout.LayoutParams
        val lpBottomLeft = vBottomLeft.layoutParams as RelativeLayout.LayoutParams
        val lpBottomRight = vBottomRight.layoutParams as RelativeLayout.LayoutParams
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
    }

    fun release() {
        if (roundedView != null) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.removeView(roundedView)
            roundedView?.destroyDrawingCache()
            roundedView = null
            System.gc()
        }
    }
}