package com.katana.koin.data

import com.katana.koin.data.local.db.DbHelper
import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.remote.ApiHelper

/**
 * Created by tomiwa on 10:14 2018-12-19
 */
interface DataManager : PrefsHelper, DbHelper,ApiHelper