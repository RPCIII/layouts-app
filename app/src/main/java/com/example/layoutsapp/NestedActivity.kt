package com.example.layoutsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

open class NestedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets dark mode and text size after button click
        //remembers settings
        val settings = getSharedPreferences("com.example.layoutsapp", Context.MODE_PRIVATE)
        var fontSizePref = settings.getString("FONT_SIZE", "Medium")
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

        //nightmode settings
        if(nightModePref == getString(R.string.on)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if(nightModePref == getString(R.string.off)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_nested)

        //sets settings button IDs programmatically
        val fontButton = findViewById<Button>(R.id.smallMediumLarge)
        fontButton.text = fontSizePref

        val nightButton = findViewById<Button>(R.id.onOff)
        nightButton.text = nightModePref

    }

    //back to main, creates new instance to reload settings
    fun goBack(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    //sets text size globally
    fun changeTextSize(view: View) {

        //variables to cycle through array and prepare to set text size
        var fontSizePref = ""
        val button = findViewById<Button>(R.id.smallMediumLarge)
        val options =
            arrayOf(getString(R.string.smallText), getString(R.string.mediumText), getString(R.string.largeText))
        val current = button.text
        val index = options.indexOf(current)
        var newIndex: Int = -1


        //open and edit preferences to store changes
        val settings = getSharedPreferences("com.example.layoutsapp", Context.MODE_PRIVATE)
        val editSettings: SharedPreferences.Editor = settings.edit()

        //uses index of array to switch between text sizes
        if (index == 0) {
            newIndex = 1
            fontSizePref = getString(R.string.mediumText)
            Log.d("TAG", fontSizePref)
        } else if (index == 1) {
            newIndex = 2
            fontSizePref = getString(R.string.largeText)
            Log.d("TAG", fontSizePref)
        } else if (index == 2) {
            newIndex = 0
            fontSizePref = getString(R.string.smallText)
            Log.d("TAG", fontSizePref)
        }

        //sets button text, commits changes to be remembered
        button.text = options[newIndex]
        editSettings.putString("FONT_SIZE", fontSizePref)
        editSettings.commit()

        //recreate to reload activity with new settings
        recreate()

    }

    //toggles night mode/dark theme
    fun nightModeToggle(view: View) {

        val button = findViewById<Button>(R.id.onOff)
        val buttonText = button.text

        //open and edit preferences to store changes
        val settings = getSharedPreferences("com.example.layoutsapp", Context.MODE_PRIVATE)
        val editSettings: SharedPreferences.Editor = settings.edit()


        //sets button sizes and commits setting changes to be remembered
        if(buttonText == getString(R.string.on)) editSettings.putString("DARK_MODE", getString(R.string.off))
        else if(buttonText == getString(R.string.off)) editSettings.putString("DARK_MODE", getString(R.string.on))
        editSettings.commit()

        //recreate to reload activity with new settings
        recreate()
    }

    //does something; I was out of ideas for simple settings, this is
    //just a toss in for an additional layout.
    //Shows text when clicked
    fun doNothing(view: View) {
        val textView = findViewById<TextView>(R.id.doesThis)
        textView.text = getString(R.string.except)
    }

}
