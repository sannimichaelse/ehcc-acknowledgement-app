package com.katana.koin.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.katana.koin.data.AppDataManager
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.db.*
import com.katana.koin.data.local.prefs.AppPrefsHelper
import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.remote.AppApiHelper
import com.katana.koin.ui.MainViewModel
import com.katana.koin.ui.contacts.ContactViewModel
import com.katana.koin.ui.history.HistoryViewModel
import com.katana.koin.ui.home.HomeViewModel
import com.katana.koin.ui.login.LoginViewModel
import com.katana.koin.ui.send_mail.MailViewModel
import com.katana.koin.ui.splash.SplashViewModel
import com.utils.SchedulerProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by tomiwa on 10:16 2018-12-19
 */
//define app module gson, data manager, etc...
val appModule: Module = module {

    single { SchedulerProvider() }

    single { AppPrefsHelper(get(), "Katana", get()) as PrefsHelper }

    single { AppDataManager(get(), AppDbHelper(get()),  AppApiHelper() ) as DataManager }

    single { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!! }

    single {  Room.databaseBuilder(get(), AcknowledgementDatabase::class.java, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build() }

}

//define list view model
val viewModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MailViewModel(get(), get()) }
    viewModel { HistoryViewModel(get(), get()) }
    viewModel { ContactViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
}


val mvvmModule = listOf(appModule, viewModule)