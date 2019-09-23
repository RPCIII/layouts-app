package com.example.layoutsapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets font size dependent on settings
        val settings = getSharedPreferences("com.example.layoutsapp", Context.MODE_PRIVATE)
        var fontSizePref = settings.getString("FONT_SIZE", "test")
        var nightModePref = settings.getString("DARK_MODE", "Off")
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

        //sets dark mode on create; remembers save settings if app closes
        if(nightModePref == getString(R.string.on)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if(nightModePref == getString(R.string.off)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)
    }

    //gets buttton id and opens corresponding activity
    fun onClick(view: View) {

        val activity: Int

        when(view.id) {

            R.id.VertLinear -> startActivity(Intent(this, VertLinear::class.java))

            R.id.HorLinear -> startActivity(Intent(this, HorLinear::class.java))

            R.id.TableActivity -> startActivity(Intent(this, TableActivity::class.java))

            R.id.FrameActivity -> startActivity(Intent(this, FrameActivity::class.java))

            R.id.NestedActivity -> startActivity(Intent(this, NestedActivity::class.java))

        }

    }
}
