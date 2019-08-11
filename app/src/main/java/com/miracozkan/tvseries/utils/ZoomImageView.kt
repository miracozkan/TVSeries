package com.miracozkan.tvseries.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 07.08.2019 - 17:59            │
//└─────────────────────────────┘

class ZoomImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs), View.OnTouchListener {

    private val mMatrix: Matrix = Matrix()

    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2
    private var mode = NONE

    private val last = PointF()
    private val start = PointF()
    private var minScale = 1f
    private var maxScale = 3f
    private val m = FloatArray(9) { 0f }

    private var redundantXSpace = 0f
    private var redundantYSpace = 0f

    private var mWidth = 0
    private var mHeight = 0
    private val CLICK = 3
    private var saveScale = 1f
    private var right = 0f
    private var bottom = 0f
    private var originalWidth = 0f
    private var originalHeight = 0f
    private var bmWidth = 0
    private var bmHeight = 0

    private val mScaleDetector = ScaleGestureDetector(context, ScaleListener())

    init {
        isClickable = true
        mMatrix.setTranslate(1f, 1f)
        imageMatrix = mMatrix
        scaleType = ScaleType.MATRIX

        setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        mScaleDetector.onTouchEvent(event)

        mMatrix.getValues(m)
        val x = m[Matrix.MTRANS_X]
        val y = m[Matrix.MTRANS_Y]
        val curr = PointF(event!!.x, event.y)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                last.set(event.x, event.y)
                start.set(last)
                mode = DRAG
            }
            MotionEvent.ACTION_MOVE -> {
                when (mode) {
                    DRAG -> {
                        var deltaX = curr.x - last.x
                        var deltaY = curr.y - last.y
                        val scaleWidth = Math.round(originalWidth * saveScale)
                        val scaleHeight = Math.round(originalHeight * saveScale)

                        when {
                            scaleWidth < mWidth -> {
                                deltaX = 0f
                                if (y + deltaY > 0)
                                    deltaY = -y
                                else if (y + deltaY < -bottom)
                                    deltaY = -(y + bottom)
                            }
                            scaleHeight < mHeight -> {
                                deltaY = 0f
                                if (x + deltaX > 0)
                                    deltaX = -x
                                else if (x + deltaX < -right)
                                    deltaX = -(x + right)
                            }
                            else -> {
                                if (x + deltaX > 0)
                                    deltaX = -x
                                else if (x + deltaX < -right)
                                    deltaX = -(x + right)
                                if (y + deltaY > 0)
                                    deltaY = -y
                                else if (y + deltaY < -bottom)
                                    deltaY = -(y + bottom)
                            }
                        }
                        mMatrix.postTranslate(deltaX, deltaY)
                        last.set(curr.x, curr.y)
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                mode = NONE
                val xDiff = Math.abs(curr.x - start.x).toInt()
                val yDiff = Math.abs(curr.y - start.y).toInt()
                if (xDiff < CLICK && yDiff < CLICK)
                    performClick()
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
            }
        }
        imageMatrix = mMatrix
        invalidate()
        return true
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        bm?.let {
            bmWidth = it.width
            bmHeight = it.height
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        val scaleX = mWidth.toFloat() / bmWidth.toFloat()
        val scaleY = mHeight.toFloat() / bmHeight.toFloat()
        val scale = Math.min(scaleX, scaleY)
        mMatrix.setScale(scale, scale)
        imageMatrix = mMatrix
        saveScale = 1f

        redundantXSpace = mWidth.toFloat() - (scale * bmWidth.toFloat())
        redundantXSpace /= 2f
        redundantYSpace = mHeight.toFloat() - (scale * bmHeight.toFloat())
        redundantYSpace /= 2f

        mMatrix.postTranslate(redundantXSpace, redundantYSpace)

        originalWidth = mWidth - 2 * redundantXSpace
        originalHeight = mHeight - 2 * redundantYSpace

        right = mWidth * saveScale - mWidth - (2 * redundantXSpace * saveScale)
        bottom = mHeight * saveScale - mHeight - (2 * redundantYSpace * saveScale)
        imageMatrix = mMatrix


    }


    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            mode = ZOOM
            return true
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            detector?.let { _detector ->
                var mScaleFactor = Math.min(Math.max(.95f, _detector.scaleFactor), 1.05f)
                val originalScale = saveScale
                saveScale *= mScaleFactor
                if (saveScale > maxScale) {
                    saveScale = maxScale
                    mScaleFactor = maxScale / originalScale
                } else if (saveScale < minScale) {
                    saveScale = minScale
                    mScaleFactor = minScale / originalScale
                }
                right = mWidth * saveScale - mWidth - (2 * redundantXSpace * saveScale)
                bottom = mHeight * saveScale - mHeight - (2 * redundantYSpace * saveScale)

                if (originalWidth * saveScale <= mWidth || originalHeight * saveScale <= mHeight) {
                    mMatrix.postScale(mScaleFactor, mScaleFactor, (mWidth / 2).toFloat(), (mHeight / 2).toFloat())
                    if (mScaleFactor < 1) {
                        mMatrix.getValues(m)
                        val x = m[Matrix.MTRANS_X]
                        val y = m[Matrix.MTRANS_Y]
                        if (mScaleFactor < 1) {
                            if (Math.round(originalWidth * saveScale) < mWidth) {
                                if (y < -bottom)
                                    mMatrix.postTranslate(0f, -(y + bottom))
                                else if (y > 0)
                                    mMatrix.postTranslate(0f, -y)
                            } else {
                                if (x < -right)
                                    mMatrix.postTranslate(-(x + right), 0f)
                                else if (x > 0)
                                    mMatrix.postTranslate(-x, 0f)
                            }
                        }
                    }
                } else {
                    mMatrix.postScale(mScaleFactor, mScaleFactor, _detector.focusX, _detector.focusY)
                    mMatrix.getValues(m)
                    val x = m[Matrix.MTRANS_X]
                    val y = m[Matrix.MTRANS_Y]

                    if (mScaleFactor < 1) {
                        if (x < -right)
                            mMatrix.postTranslate(-(x + right), 0f)
                        else if (x > 0)
                            mMatrix.postTranslate(-x, 0f)
                        if (y < -bottom)
                            mMatrix.postTranslate(0f, -(y + bottom))
                        else if (y > 0)
                            mMatrix.postTranslate(0f, -y)
                    }
                }
            }
            return true
        }
    }
}