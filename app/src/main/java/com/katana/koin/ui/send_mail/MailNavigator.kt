package com.katana.koin.ui.send_mail

import com.katana.koin.data.remote.model.MailResponse
import io.reactivex.Single

interface MailNavigator {

    fun handleMailResponse(result: MailResponse)

    fun handleSaveMailResponse(result: Long)

    fun handleMailError(error: Throwable)
}