package com.example.light_dark

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

public class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       //using shared preference saving the user's response
        val sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
        val editMode = sharedPreferences.edit()
        val darkModeON = sharedPreferences.getBoolean("DarkMode",false)

        //using switch for toggling between light/dark mode
        val switch : Switch
        switch = findViewById(R.id.switch1)

        //getting the user's default mode from settings
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                // Night mode is not active, we're using the light theme
                switch.text = "Light"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editMode.putBoolean("DarkMode",false)
                editMode.apply()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                // Night mode is active, we're using dark theme
                switch.text = "Dark"
                switch.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editMode.putBoolean("DarkMode",true)
                editMode.apply()
            }
        }

        //while opening tha app checking the shared preference whether dark is in ON or not
        if(darkModeON){
            switch.text = "Dark"
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switch.isChecked = true
        }

        //setting on Checked listener for switch
        switch.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){
                //when the switch is ON dark mode is ON
                switch.text = "Dark"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editMode.putBoolean("DarkMode",true)
                editMode.apply()
            }
            else{
                //when the switch is OFF light mode is ON
                switch.text = "Light"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editMode.putBoolean("DarkMode",false)
                editMode.apply()
            }
        }
    }
}