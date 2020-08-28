package com.katana.koin.ui.home

import android.os.Bundle
import com.base.BaseFragment
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.FragmentHomeBinding
import com.utils.ext.isExistFragmentInBackStack
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun getViewModel(): HomeViewModel = homeViewModel

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getBindingVariable(): Int = BR.homeModel

    override fun updateUI(savedInstanceState: Bundle?) {
        homeViewModel.setNavigator(this)

        val a = isExistFragmentInBackStack<HomeFragment>(R.id.content_home)
    }
}
