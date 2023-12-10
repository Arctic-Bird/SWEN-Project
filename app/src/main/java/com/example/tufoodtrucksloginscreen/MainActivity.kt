package com.example.tufoodtrucksloginscreen


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.example.tufoodtrucksloginscreen.ui.login.LoginFragment
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private var toTrucks: Button? = null // Create To Trucks button on login screen
    private var alanButton: AlanButton? = null // Create the Alan button
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

        val config = AlanConfig.builder().setProjectId("2775d12cbe1b36019bba239f38a422b82e956eca572e1d8b807a3e2338fdd0dc/stage").build()
        alanButton = findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)


        fun callProjectApi() {
            /// Providing any params
            val params = JSONObject()
            try {
                params.put("data", "your data")
            } catch (e: JSONException) {
                Log.e("AlanButton", e.message.toString())
            }
            alanButton?.callProjectApi("script::funcName", params.toString())
        }

        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan AI Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    Log.d("AlanButton", "onCommand: commandName: $commandName")
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };
        alanButton?.registerCallback(alanCallback);
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