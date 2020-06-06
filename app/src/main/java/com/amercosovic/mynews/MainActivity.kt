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

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, MyNewsDataFragment())
            commit()
        }

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "Top Stories") {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, MyNewsDataFragment())
                        commit()
                    }
                } else if (tab?.text == "Most Popular") {
                    val bundle = Bundle()
                    val mostPopularPath = "svc/mostpopular/v2/viewed/1.jsonsvc/mostpopular/v2/viewed/1.json"
                    bundle.putString("mostPopularPath", mostPopularPath)
                    val fragInfo = MyNewsDataFragment()
                    fragInfo.setArguments(bundle)
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, fragInfo)
                        commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab?.text == "Most Popular") {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, MyNewsDataFragment())
                        commit()
                    }
                } else if(tab?.text == "Top Stories") {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, MyNewsDataFragment())
                        commit()
                    }
                }


//        most_popular_tab.setOnClickListener{
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.frame_layout, MostPopularFragment())
//                commit()
//            }
//        }


            }
        })
    }
}





