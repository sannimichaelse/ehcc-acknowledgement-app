package com.katana.koin.data.local.prefs

import com.katana.koin.data.DataManager

/**
 * Created by tomiwa on 10:38 2018-12-19
 */
interface PrefsHelper {

    fun getUser(): String?

    fun saveUser(user: String)

    fun getLoginMode(): Boolean

    fun saveLoginMode(mode: Boolean)

}