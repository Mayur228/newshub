package com.theappmakerbuddy.newshub.common.logger

import android.util.Log
import com.theappmakerbuddy.newshub.common.logger.Logger

class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}