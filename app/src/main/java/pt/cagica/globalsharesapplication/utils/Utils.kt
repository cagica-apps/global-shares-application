package pt.cagica.globalsharesapplication.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

object CommonUtils {

    fun isNetworkAvailable(activity: Application?): Boolean {
        if (activity == null) {
            return false
        }
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
