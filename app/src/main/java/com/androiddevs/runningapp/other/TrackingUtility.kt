package com.androiddevs.runningapp.other

import android.Manifest
import android.content.Context
import android.os.Build
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

class TrackingUtility {

    companion object {
        fun hasLocationPermissions(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                EasyPermissions.hasPermissions(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } else {
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
                EasyPermissions.hasPermissions(context, *permissions)
            }
        }

        fun getFormattedTimeWithSeconds(ms: Long): String {
            var milliseconds = ms
            val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
            milliseconds -= TimeUnit.HOURS.toMillis(hours)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
            milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
            milliseconds -= TimeUnit.SECONDS.toMillis(seconds)
            milliseconds /= 10
            return "${if(hours < 10) "0" else ""}$hours:" +
                    "${if(minutes < 10) "0" else ""}$minutes:" +
                    "${if(seconds < 10) "0" else ""}$seconds:" +
                    "${if(milliseconds < 10) "0" else ""}$milliseconds"
        }
    }
}