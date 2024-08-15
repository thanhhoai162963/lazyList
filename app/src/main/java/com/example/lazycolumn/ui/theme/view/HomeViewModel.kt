package com.example.lazycolumn.ui.theme.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//B1
class HomeViewModel : ViewModel() {

    fun caculateLogic(): Int {
        var temp = 0
        for (i in 0..100) {
            temp += i
        }
        return temp
    }

    override fun onCleared() {
        super.onCleared()
    }
}


