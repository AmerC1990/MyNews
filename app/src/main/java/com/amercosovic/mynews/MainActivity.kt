package com.amercosovic.mynews


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
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
                    "Sports" -> {
                        val fragment = MyNewsDataFragment.newInstance(SPORTS)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu?.findItem(R.id.searchIcon)
        menuItem?.setOnMenuItemClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {

        super.onResume()
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
            const val SPORTS = "sports"
        }
    }






