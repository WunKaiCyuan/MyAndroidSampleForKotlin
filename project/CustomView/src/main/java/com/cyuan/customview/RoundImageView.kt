package com.cyuan.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap


class RoundImageView : AppCompatImageView {

    private val paint: Paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        if (drawable != null) {

            val newWidth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                width - paddingStart - paddingEnd
            } else {
                width - paddingLeft - paddingRight
            }
            val newHeight = height - paddingTop - paddingBottom

            val bitmap = drawable.toBitmap(
                newWidth,
                newHeight
            )

            //核心程式碼
            val b: Bitmap = getCircleBitmap(bitmap!!)
            paint.reset()
            canvas!!.drawBitmap(b, paddingLeft.toFloat(), paddingTop.toFloat(), paint)
        } else {
            super.onDraw(canvas)
        }
    }

    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(
            bitmap.width,
            bitmap.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        paint.isAntiAlias = true
        canvas.drawCircle(
            bitmap.width / 2.toFloat(),
            bitmap.height / 2.toFloat(),
            bitmap.height / 2.toFloat(),
            paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}