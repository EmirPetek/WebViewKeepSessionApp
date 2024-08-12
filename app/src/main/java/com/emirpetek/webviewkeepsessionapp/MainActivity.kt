package com.emirpetek.webviewkeepsessionapp

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.emirpetek.webviewkeepsessionapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        binding.webview.webViewClient = WebViewClient()

        // this will load the url of the website
        binding.webview.loadUrl(getString(R.string.base_url))

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.webview.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        binding.webview.settings.setSupportZoom(true)


        // WebViewClient'i ayarla
        binding.webview.webViewClient = object : WebViewClient() {

            // Sayfa yüklenmesi tamamlandığında bu metod çağrılır
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                // URL'yi kontrol et
                if (url.contains("https://unisportclub.com.tr/login")) {
                    // Belirli bir URL içeren sayfalar için Logcat'e mesaj yazdır
                    Log.e("WebView", "URL contains https://unisportclub.com.tr/login: $url")
                }
            }
        }

        setContentView(binding.root)
    }



    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (binding.webview.canGoBack())
            binding.webview.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }


}