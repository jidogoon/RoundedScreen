package com.jidogoon.roundedscreen.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v14.preference.SwitchPreference
import android.support.v7.app.AlertDialog
import android.support.v7.preference.*
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
    var prefCateAppearance: PreferenceCategory? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
        prefRoundEnable = findPreference(getString(R.string.pref_key_round_enable)) as SwitchPreference?
        prefStartOnBoot = findPreference(getString(R.string.pref_key_start_on_boot)) as SwitchPreference?
        prefCateAppearance = findPreference(getString(R.string.pref_cate_key_appearance)) as PreferenceCategory
        prefRoundEnable?.onPreferenceChangeListener = this
        prefStartOnBoot?.onPreferenceChangeListener = this

        findPreference(getString(R.string.pref_key_round_size)).onPreferenceChangeListener = this
        findPreference(getString(R.string.pref_key_round_top_left)).onPreferenceChangeListener = this
        findPreference(getString(R.string.pref_key_round_top_right)).onPreferenceChangeListener = this
        findPreference(getString(R.string.pref_key_round_bottom_left)).onPreferenceChangeListener = this
        findPreference(getString(R.string.pref_key_round_bottom_right)).onPreferenceChangeListener = this

        findPreference(getString(R.string.pref_key_github)).onPreferenceClickListener = this
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
        else if (preference?.key.equals(getString(R.string.pref_key_round_top_left))) {
            options.topLeft = newValue as Boolean
            (preference as CheckBoxPreference).isChecked = newValue
            tryStartRoundedService(options)
        }
        else if (preference?.key.equals(getString(R.string.pref_key_round_top_right))) {
            options.topRight = newValue as Boolean
            (preference as CheckBoxPreference).isChecked = newValue
            tryStartRoundedService(options)
        }
        else if (preference?.key.equals(getString(R.string.pref_key_round_bottom_left))) {
            options.bottomLeft = newValue as Boolean
            (preference as CheckBoxPreference).isChecked = newValue
            tryStartRoundedService(options)
        }
        else if (preference?.key.equals(getString(R.string.pref_key_round_bottom_right))) {
            options.bottomRight = newValue as Boolean
            (preference as CheckBoxPreference).isChecked = newValue
            tryStartRoundedService(options)
        }
        return false
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        if (preference?.key.equals(getString(R.string.pref_key_github))) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_url)))
            activity.startActivity(intent)
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        if (!prefRoundEnable?.isChecked!!) {
            stopRoundedService()
            return
        }
        tryStartRoundedService(getOptions())
    }

    fun getOptions(): RoundedViewOptions {
        val options = RoundedViewOptions()
        options.size = (findPreference(getString(R.string.pref_key_round_size)) as SeekBarPreference).value
        options.topLeft = (findPreference(getString(R.string.pref_key_round_top_left)) as CheckBoxPreference).isChecked
        options.topRight = (findPreference(getString(R.string.pref_key_round_top_right)) as CheckBoxPreference).isChecked
        options.bottomLeft = (findPreference(getString(R.string.pref_key_round_bottom_left)) as CheckBoxPreference).isChecked
        options.bottomRight = (findPreference(getString(R.string.pref_key_round_bottom_right)) as CheckBoxPreference).isChecked
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
        prefStartOnBoot?.isEnabled = true
        prefCateAppearance?.isEnabled = true
    }

    fun stopRoundedService() {
        val intent = Intent(context, RoundedService::class.java)
        context.stopService(intent)
        prefRoundEnable?.isChecked = false
        prefStartOnBoot?.isEnabled = false
        prefCateAppearance?.isEnabled = false
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