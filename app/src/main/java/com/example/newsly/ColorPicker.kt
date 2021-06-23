package com.example.newsly

object ColorPicker {

    private val colors = arrayOf("#3eb9df", "#3685bc", "#d36280", "#e44f55", "#fa8056",
                            "#818bca", "#7d659f", "#51bab3", "#4fb66c", "#e3ad17",
                            "#627991", "#ef8ead", "b5bfc6" )
    var index = 0

    fun getColor(): String {
        return colors[index++ % colors.size]
    }
}