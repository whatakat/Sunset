package com.example.sunset

import androidx.fragment.app.Fragment
import com.example.sunset.SunsetFragment.Companion.newInstance

class SunsetActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return newInstance()
    }
}