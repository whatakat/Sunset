package com.example.sunset

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment

class SunsetFragment : Fragment() {
    private var mSceneView: View? = null
    private var mSunView: View? = null
    private var mSkyView: View? = null
    private var mBlueSkyColor = 0
    private var mSunsetSkyColor = 0
    private var mNightSkyColor = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sunset, container, false)
        mSceneView = view
        mSunView = view.findViewById(R.id.sun)
        mSkyView = view.findViewById(R.id.sky)
        val resources = resources
        mBlueSkyColor = resources.getColor(R.color.blue_sky)
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky)
        mNightSkyColor = resources.getColor(R.color.night_sky)
        mSceneView!!.setOnClickListener { startAnimation() }
        return view
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startAnimation() {
        val sunYStart = mSunView!!.top.toFloat()
        val sunYEnd = mSkyView!!.height.toFloat()
        val heightAnimator = ObjectAnimator
                .ofFloat(mSunView, "y", sunYStart, sunYEnd)
                .setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator(2F)
        val sunsetSkyAnimator = ObjectAnimator
                .ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                .setDuration(3000)
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())
        val nightSkyAnimator = ObjectAnimator
                .ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor)
                .setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())
        val animatorSet = AnimatorSet()
        animatorSet
                .play(heightAnimator)
                .with(sunsetSkyAnimator)
                .before(nightSkyAnimator)
        animatorSet.start()
    }

    companion object {
        @JvmStatic
        fun newInstance(): SunsetFragment {
            return SunsetFragment()
        }
    }
}