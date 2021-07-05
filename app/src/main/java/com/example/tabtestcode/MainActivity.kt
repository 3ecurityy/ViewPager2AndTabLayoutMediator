package com.example.tabtestcode

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tabtestcode.databinding.ActivityMainBinding
import com.example.tabtestcode.ui.main.PlaceholderFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    var type: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)
        tabLayout.visibility = ViewPager2.VISIBLE

        val fragmentList: ArrayList<PlaceholderFragment> = arrayListOf(
            PlaceholderFragment.newInstance(1),
            PlaceholderFragment.newInstance(2),
            PlaceholderFragment.newInstance(3)
        )

        viewPager.adapter = ViewPagerAdapter(this, fragmentList)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                TabLayout.TabLayoutOnPageChangeListener(tabLayout)
                when (position) {
                    0 -> {
                        type = ""
                    }
                    1 -> {
                        type = "public"
                    }
                    2 -> {
                        type = "private"
                    }
                }
            }
        })

        val betweenSpace = 100
        val slidingTabStrip = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until slidingTabStrip.childCount - 1) {
            val v = slidingTabStrip.getChildAt(i)
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.rightMargin = betweenSpace
        }

        initializeTabLayout()
    }

    class ViewPagerAdapter(
        fa: FragmentActivity,
        private val fragments: ArrayList<PlaceholderFragment>
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    private fun initializeTabLayout() {
        TabLayoutMediator(tabLayout, viewPager, true) { tab, position -> // Styling each tab here
            when (position) {
                0 -> {
                    tab.text = "1"
                }
                1 -> {
                    tab.text = "2"
                }
                2 -> {
                    tab.text = "3"
                }
            }

        }.attach()
    }

}