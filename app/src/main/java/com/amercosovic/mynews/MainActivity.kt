package com.amercosovic.mynews


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.fragments.MyNewsDataFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set MyNewsDataFragment to frame layout in Main Activity and pass instance of
        // top stories to fragment
        val fragment = MyNewsDataFragment.newInstance(TOP_STORIES)
        replaceFragment(fragment)

        // When tab is selected, pass new instance to fragment and set fragment to framelayout
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

    // Display Options Menu.. Search Icon, Notifications Menu
    // Open New Activity When Notification or Search have been clicked
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.searchIcon)
        val optionsMenuItem = menu?.findItem(R.id.notifications)
        optionsMenuItem?.setOnMenuItemClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
            true
        }
        menuItem?.setOnMenuItemClickListener {
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
        // Constants for different tabs
    }

    companion object PATHS {
        const val MOST_POPULAR = "most_popular"
        const val TOP_STORIES = "top_stories"
        const val SPORTS = "sports"
    }

}






