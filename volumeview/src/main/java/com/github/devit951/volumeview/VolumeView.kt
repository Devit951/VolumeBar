package com.github.devit951.volumeview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class VolumeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): View(context, attrs, defStyle){

    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }


    private var maxVolumeCount = 0
    private var currentVolumeCount = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), dp2Px(10f).toFloat(), height / 2f, rectPaint)
        drawCircle(canvas)
    }

    @SuppressLint("SwitchIntDef")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = when(View.MeasureSpec.getMode(widthMeasureSpec)){
            View.MeasureSpec.EXACTLY -> View.MeasureSpec.getSize(View.MeasureSpec.getMode(widthMeasureSpec))
            else -> dp2Px(200f)
        }
        val height = when(View.MeasureSpec.getMode(heightMeasureSpec)){
            View.MeasureSpec.EXACTLY -> View.MeasureSpec.getSize(View.MeasureSpec.getMode(heightMeasureSpec))
            else -> dp2Px(20f)
        }
        setMeasuredDimension(width, height)
    }

    fun calibrate(maxVolumeCount: Int, currentVolumeCount: Int){
        this.maxVolumeCount = maxVolumeCount
        this.currentVolumeCount = currentVolumeCount
        invalidate()
    }

    fun setCurrentVolume(streamVolume: Int) {
        currentVolumeCount = streamVolume
        invalidate()
    }

    private fun drawCircle(canvas: Canvas){
        val radius = height / 2f
        val circleX = ((width - height) / maxVolumeCount) * currentVolumeCount.toFloat() + radius
        canvas.drawCircle(circleX, height / 2f, height / 2f, circlePaint)
    }

    private fun dp2Px(dp: Float): Int = (Resources.getSystem().displayMetrics.density * dp).toInt()
}