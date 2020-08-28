package com.katana.koin.base

import com.base.ViewModelB
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

/**
 * Created by tomiwa on 10:15 2018-12-19
 */
open class BaseViewModel<N>(var dataManager: DataManager, var schedulerProvider: SchedulerProvider) : ViewModelB<N>(schedulerProvider)