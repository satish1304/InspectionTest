package com.example.inspection.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppUtils {

    companion object {
        fun getCurrentDateTime(): String {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(formatter)
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (networkCapabilities != null) {
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        // Connected via mobile data (cellular)
                        return true
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        // Connected via Wi-Fi
                        return true
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        // Connected via Ethernet
                        return true
                    }
                }
            }
            return false
        }
    }


}