package com.example.littlelemon.data

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
