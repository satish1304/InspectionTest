package com.example.inspection.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppUtils {

    companion object {
        fun getCurrentDateTime(): String {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(formatter)
        }
    }
}