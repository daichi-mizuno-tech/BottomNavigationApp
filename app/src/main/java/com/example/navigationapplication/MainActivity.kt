package com.example.navigationapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.size
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() ,BottomNavigationView.OnNavigationItemSelectedListener{


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		initializeResource()
	}

	fun initializeResource(){
//		BottomNavigationViewを設定
		val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
		bottomNavigationView.setOnNavigationItemSelectedListener(this)
		bottomNavigationView.itemIconSize = 70
		bottomNavigationView.scaleX = 1.2f
		bottomNavigationView.scaleY = 1.2f

//		初期Fragmentを設定
		supportFragmentManager.beginTransaction()
				.replace(R.id.container,HomeFragment())
				.setReorderingAllowed(true)
				.commit()
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		Log.d(TAG,"Selected item: " + item)

		when(item.itemId){
//			ホームボタンが押された時
			R.id.action_home -> {
				supportFragmentManager.beginTransaction()
						.replace(R.id.container,HomeFragment())
						.setReorderingAllowed(true)
						.commit()
			}
//			設定ボタンが押された時
			R.id.action_setting -> {
				supportFragmentManager.beginTransaction()
						.replace(R.id.container,SettingFragment())
						.setReorderingAllowed(true)
						.commit()
			}

		}

		return true
	}

	companion object{
		const val TAG : String = "MainActivity"
	}
}