package com.jidogoon.roundedscreen.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jidogoon.roundedscreen.fragment.SettingsFragment
import com.jidogoon.roundedscreen.roundedview.RoundedViewOptions
import com.jidogoon.roundedscreen.service.RoundedService

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class MainSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        startRoundedService()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    fun startRoundedService() {
        val intent = Intent(applicationContext, RoundedService::class.java)
        val options = RoundedViewOptions()
        options.bottomRight = false
        intent.putExtra(RoundedService.INTENT_KEY, options)
        startService(intent)
    }
}
