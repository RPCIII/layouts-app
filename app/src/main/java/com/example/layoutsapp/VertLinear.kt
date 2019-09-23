package com.example.layoutsapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class VertLinear : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //sets font size dependent on settings
        val settings = getSharedPreferences("com.example.layoutsapp", Context.MODE_PRIVATE)
        var fontSizePref = settings.getString("FONT_SIZE", "test")
        var themeID = R.style.AppTheme

        //try to set text size or use default on fail
        try {
            if (fontSizePref === getString(R.string.smallText)) {
                themeID = R.style.fontSizeSmall
            } else if(fontSizePref === getString(R.string.mediumText)) {
                themeID = R.style.fontSizeMedium
            } else if(fontSizePref === getString(R.string.largeText)) {
                themeID = R.style.fontSizeLarge
            }
            setTheme(themeID)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        setContentView(R.layout.activity_vert_linear)
    }


    fun goBack(view: View) {
        finish()
    }

}
