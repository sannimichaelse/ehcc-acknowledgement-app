package com.katana.koin.data.remote

import com.katana.koin.data.remote.model.MailResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class AppApiHelper constructor() : ApiHelper {

    override fun sendMail(params: MutableMap<String, Any>): Single<MailResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.EMAIL_URL)
                .addBodyParameter(params)
                .build()
                .getObjectSingle(MailResponse::class.java)
    }
}
