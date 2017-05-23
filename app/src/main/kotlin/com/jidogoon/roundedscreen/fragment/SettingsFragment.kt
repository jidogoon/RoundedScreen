package com.jidogoon.roundedscreen.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v14.preference.SwitchPreference
import android.support.v7.app.AlertDialog
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.SeekBarPreference
import android.widget.Toast
import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.roundedview.RoundedViewOptions
import com.jidogoon.roundedscreen.service.RoundedService

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    var prefRoundEnable: SwitchPreference? = null
    var prefStartOnBoot: SwitchPreference? = null
    var prefSize: SeekBarPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
        prefRoundEnable = findPreference(getString(R.string.pref_key_round_enable)) as SwitchPreference?
        prefStartOnBoot = findPreference(getString(R.string.pref_key_start_on_boot)) as SwitchPreference?
        prefSize = findPreference(getString(R.string.pref_key_round_size)) as SeekBarPreference?
        prefRoundEnable?.onPreferenceChangeListener = this
        prefStartOnBoot?.onPreferenceChangeListener = this
        prefSize?.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        val options = getOptions()
        if (preference?.key.equals(getString(R.string.pref_key_round_enable))) {
            if (newValue as Boolean)
                tryStartRoundedService(options)
            else
                stopRoundedService()
            return true
        }
        else if (preference?.key.equals(getString(R.string.pref_key_start_on_boot))) {
            Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (preference?.key.equals(getString(R.string.pref_key_round_size))) {
            options.size = newValue as Int
            tryStartRoundedService(options)
            return true
        }
        return false
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        tryStartRoundedService(getOptions())
    }

    fun getOptions(): RoundedViewOptions {
        val options = RoundedViewOptions()
        options.size = prefSize!!.value
        return options
    }

    fun tryStartRoundedService(options: RoundedViewOptions?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasOverlayPermission()) {
            val dialog = AlertDialog.Builder(context)
                    .setMessage(R.string.permission_warning)
                    .setPositiveButton(android.R.string.ok, { _, _ ->
                        showOverlaySystemSettings()
                    })
                    .setNegativeButton(android.R.string.cancel, { _, _ ->
                        activity.finish()
                    })
                    .setOnCancelListener( { activity.finish() })
                    .create()
            dialog.show()
            return
        }
        startRoundedService(options)
    }

    fun startRoundedService(options: RoundedViewOptions?) {
        val intent = Intent(context, RoundedService::class.java)
        intent.putExtra(RoundedService.INTENT_KEY, options)
        context.startService(intent)
        prefRoundEnable?.isChecked = true
        prefSize?.isEnabled = true
    }

    fun stopRoundedService() {
        val intent = Intent(context, RoundedService::class.java)
        context.stopService(intent)
        prefRoundEnable?.isChecked = false
        prefSize?.isEnabled = false
    }

    ///////////////////////////////////////
    // request 'draw over app' permissions
    val OVERLAY_PERMISSION_REQUEST_CODE = 2000

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasOverlayPermission(): Boolean {
        return Settings.canDrawOverlays(context)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun showOverlaySystemSettings() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.packageName))
        activity.startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            startRoundedService(RoundedViewOptions())
        }
    }
}