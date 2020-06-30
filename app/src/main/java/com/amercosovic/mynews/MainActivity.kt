package com.amercosovic.mynews


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.fragments.MyNewsDataFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MyNewsDataFragment.newInstance(TOP_STORIES)
        replaceFragment(fragment)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Top Stories" -> {
                        val fragment = MyNewsDataFragment.newInstance(TOP_STORIES)
                        replaceFragment(fragment)
                    }
                    "Most Popular" -> {
                        val fragment = MyNewsDataFragment.newInstance(MOST_POPULAR)
                        replaceFragment(fragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun replaceFragment(fragment: MyNewsDataFragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }

    companion object PATHS {
        const val MOST_POPULAR = "most_popular"
        const val TOP_STORIES = "top_stories"
    }
}





