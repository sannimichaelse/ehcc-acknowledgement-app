package com.katana.koin.data.remote

import com.google.gson.JsonObject
import com.katana.koin.data.remote.model.MailResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tomiwa on 09:55 8/28/18
 */
interface ApiHelper {

    fun sendMail(params: MutableMap<String, Any>): Single<MailResponse>

}