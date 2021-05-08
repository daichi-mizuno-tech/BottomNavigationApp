package com.example.navigationapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class SettingFragment : Fragment() {
    lateinit var  mySharedPrefs : MySharedPrefs

    lateinit var saveButton: Button
    lateinit var urlText : TextView
    lateinit var cacheRadioGroup : RadioGroup
    lateinit var zoomText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        initializeResource(view)

        return view
    }

    fun initializeResource(view : View){
//        SharedPreferenceのインスタンス生成
        mySharedPrefs = MySharedPrefs(context!!)

//        保存ボタンの初期設定
        saveButton = view.findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            saveData(saveButton)
        }

//        埋め込みURLの初期設定
        urlText = view.findViewById<TextView>(R.id.url_plaintext)
        urlText.text = mySharedPrefs.getUrl()

//        キャッシュタイプラジオボタンの初期設定
        cacheRadioGroup = view.findViewById<RadioGroup>(R.id.cache_radiogroup)
        when (mySharedPrefs.getCache()){
            MySharedPrefs.CACHE_DEFAULT -> cacheRadioGroup.check(R.id.cache_default_radiobutton)
            MySharedPrefs.NO_CACHE -> cacheRadioGroup.check(R.id.nocache_radiobutton)
            MySharedPrefs.CACHE_ONLY -> cacheRadioGroup.check(R.id.cacheonly_radiobutton)
        }

//        ズーム倍率の初期設定
        zoomText = view.findViewById<TextView>(R.id.textzoom_plaintext)
        zoomText.text = mySharedPrefs.getZoom().toString()
    }


    fun saveData(view : View){
//        データの取得
        Log.d(TAG,"save datas")
        val url = urlText.text.toString()
        val checkedRadioButton = cacheRadioGroup.checkedRadioButtonId
        var cacheType = MySharedPrefs.CACHE_DEFAULT
        when(checkedRadioButton){
            R.id.cache_default_radiobutton -> cacheType = MySharedPrefs.CACHE_DEFAULT
            R.id.nocache_radiobutton -> cacheType = MySharedPrefs.NO_CACHE
            R.id.cacheonly_radiobutton -> cacheType = MySharedPrefs.CACHE_ONLY
        }
        val zoom = Integer.parseInt(zoomText.text.toString())

//        データの保存
        mySharedPrefs.setUrl(url)
        mySharedPrefs.setCache(cacheType)
        mySharedPrefs.setZoom(zoom)

        Toast.makeText(context,"データが更新されました。",Toast.LENGTH_LONG).show()
    }

    companion object {
       const val TAG : String = "SettingFragment"
    }
}