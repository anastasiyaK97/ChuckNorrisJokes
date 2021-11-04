package com.example.hdhgtestjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hdhgtestjokes.data.api.InternetConnectionCheckable
import com.example.hdhgtestjokes.databinding.MainActivityBinding
import com.example.hdhgtestjokes.presentation.view.ApiInfoFragment
import com.example.hdhgtestjokes.presentation.view.JokesFragment

class MainActivity : AppCompatActivity(), InternetConnectionCheckable {

    private lateinit var binding: MainActivityBinding
    private var apiInfoFragment: ApiInfoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        binding = MainActivityBinding.inflate(inflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            loadFragment(JokesFragment.newInstance())
        }
        initBottomNavigation()
        requestLayouts()

        binding.btnTryAgain.setOnClickListener {
            requestLayouts()
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_joke -> {
                    loadFragment(JokesFragment.newInstance())
                }
                R.id.nav_web -> {
                    apiInfoFragment = ApiInfoFragment.newInstance()
                    loadFragment(apiInfoFragment!!)
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    override fun onBackPressed() {
        if (apiInfoFragment != null && apiInfoFragment!!.canGoBack()) {
            apiInfoFragment!!.goBack();
        } else {
            super.onBackPressed();
        }
    }
    fun requestLayouts() {
        if (isInternetAvailable()) {
            binding.noInternetLayout.visibility = View.GONE
            binding.withInternetLayout.visibility = View.VISIBLE
        } else {
            binding.noInternetLayout.visibility = View.VISIBLE
            binding.withInternetLayout.visibility = View.GONE
        }
    }

}