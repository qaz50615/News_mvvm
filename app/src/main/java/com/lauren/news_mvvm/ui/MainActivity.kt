package com.lauren.news_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.lauren.news_mvvm.R
import com.lauren.news_mvvm.ui.breakingNews.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {

        val navController = findNavController(R.id.main_fragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.breakingNews_item -> {
                    hideFragment()
                    navController.navigate(R.id.newsFragment)
                }
                R.id.savedNews_item -> {
                    hideFragment()
                    navController.navigate(R.id.savedFragment)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun hideFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_fragment)?:NewsFragment()
        supportFragmentManager.beginTransaction().hide(fragment).commit()
    }
}