package com.coderlab.dagger2dfm.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.*

object LanguageHelper {
    internal const val LANG_EN = "en"

    internal const val LANG_PL = "pl"

    private const val PREFS_LANG = "language"


    lateinit var prefs: SharedPreferences
    var language: String
        get() {
            return prefs.getString(PREFS_LANG, LANG_EN)!!
        }
        set(value) {
            prefs.edit().putString(PREFS_LANG, value).apply()
        }

    fun init(ctx: Context){
        prefs = ctx.getSharedPreferences(PREFS_LANG, Context.MODE_PRIVATE)
    }

    /**
     * Get a Context that overrides the language selection in the Configuration instance used by
     * getResources() and getAssets() by one that is stored in the LanguageHelper preferences.
     *
     * @param ctx a base context to base the new context on
     */
    fun getLanguageConfigurationContext(ctx: Context): Context {
        val conf = getLanguageConfiguration()
        return ctx.createConfigurationContext(conf)
    }

    /**
     * Get an empty Configuration instance that only sets the language that is
     * stored in the LanguageHelper preferences.
     * For use with Context#createConfigurationContext or Activity#applyOverrideConfiguration().
     */
    fun getLanguageConfiguration(): Configuration {
        val conf = Configuration()
        conf.setLocale(Locale.forLanguageTag(language))
        return conf
    }

}