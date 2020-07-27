package com.cyuan.mysampleforkotlin

import java.util.concurrent.TimeUnit

class MyTimeUnit {

    val TAG = "MyTimeUnit"

    fun convertMillisecondsToTime(value: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(value)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(value) % 60
        val seconds = (TimeUnit.MILLISECONDS.toSeconds(value) % 60)
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}