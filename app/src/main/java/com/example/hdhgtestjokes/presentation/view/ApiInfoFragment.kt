package com.example.hdhgtestjokes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hdhgtestjokes.MainActivity
import com.example.hdhgtestjokes.data.api.InternetConnectionCheckable
import com.example.hdhgtestjokes.databinding.ApiInfoFragmentBinding
import com.example.hdhgtestjokes.presentation.viewmodel.ApiInfoViewModel


class ApiInfoFragment : Fragment(), InternetConnectionCheckable {

    companion object {
        fun newInstance() = ApiInfoFragment()
    }

    private lateinit var viewModel: ApiInfoViewModel
    private lateinit var binding: ApiInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ApiInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ApiInfoViewModel.Factory(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory).get(ApiInfoViewModel::class.java)
        viewModel.webView.observe(viewLifecycleOwner, { webView: WebView ->
            binding.container.addView(webView)
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (isInternetAvailable()) {
                        webView.loadUrl(url!!)
                    } else {
                        if (activity is MainActivity) {
                            (activity as MainActivity).requestLayouts()
                        }
                    }
                    return true
                }
            }
        })
    }

    fun canGoBack(): Boolean {
        return viewModel.webView.value?.canGoBack()?:false
    }

    fun goBack() {
        viewModel.webView.value?.goBack()
    }

}