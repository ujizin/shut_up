package br.com.shutup.data.local

import android.content.Context

class PreferencesHelper(context: Context) {

    companion object {
        private const val PREFERENCES_HELPER = "PREFERENCES_HELPER"
        private const val IP_CONFIG = "IP_CONFIG"
    }

    private val pref = context.getSharedPreferences(PREFERENCES_HELPER, 0)

    private fun getString(name: String, defaultValue: String? = ""): String {
        return pref.getString(name, defaultValue) ?: ""
    }

    private fun setString(name: String, value: String?) {
         pref.edit().putString(name, value).apply()
    }

    fun getIpConfig() = getString(IP_CONFIG)

    fun setIpConfig(value: String?) {
        setString(IP_CONFIG, value)
    }

}