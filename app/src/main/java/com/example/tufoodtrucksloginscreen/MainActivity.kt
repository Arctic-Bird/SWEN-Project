package com.example.tufoodtrucksloginscreen


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.example.tufoodtrucksloginscreen.ui.login.LoginFragment


class MainActivity : AppCompatActivity() {
    private var toTrucks: Button? = null
    private var alanButton: AlanButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val pagerAdapter = AuthenticationPagerAdapter(
            supportFragmentManager
        )
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(fragment_register())
        viewPager.adapter = pagerAdapter
        toTrucks = findViewById<Button>(R.id.button6)
        toTrucks!!.setOnClickListener {
            val i = Intent(this, TruckDisplayLoad::class.java)
            startActivity(i)
        }

        val config = AlanConfig.builder().setProjectId("").build()
        alanButton = findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)
    }


    internal inner class AuthenticationPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        private val fragmentList: ArrayList<Fragment> = ArrayList()
        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }
    }
}