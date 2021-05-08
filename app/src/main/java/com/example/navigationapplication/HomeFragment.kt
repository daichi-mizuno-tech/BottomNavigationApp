package com.example.navigationapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class HomeFragment : Fragment() {
    lateinit var  mySharedPrefs : MySharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initializeResource(view)

        return view
    }

    fun initializeResource(view : View){
//        SharedPreferenceのインスタンス生成
        mySharedPrefs = MySharedPrefs(context!!)

//        データの取得
        val url = mySharedPrefs.getUrl()
        val cacheType = mySharedPrefs.getCache()
        val zoom = mySharedPrefs.getZoom()

//        WebViewの設定
        val webView = view.findViewById<WebView>(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        when(cacheType){
            MySharedPrefs.CACHE_DEFAULT -> webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
            MySharedPrefs.NO_CACHE -> webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
            MySharedPrefs.CACHE_ONLY -> webView.settings.cacheMode = WebSettings.LOAD_CACHE_ONLY
        }

        webView.settings.textZoom = zoom
        webView.loadUrl(url!!)
    }

    companion object {
        const val TAG : String = "HomeFragment"
    }
}