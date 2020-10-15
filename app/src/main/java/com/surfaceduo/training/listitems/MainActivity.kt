package com.surfaceduo.training.listitems

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.device.dualscreen.core.ScreenHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        //Wjhen we are in spanned mode we show list-detail views
        if (ScreenHelper.isDualMode(this)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.first_container_id, ListItemsFragment())
                .replace(R.id.second_container_id, DetailFragment(), "detailFragment")
                .commit()
        }
        //when we are in single screen mode and we don't currently show the detail view means we are in the initial
        //state, when the app starts
        else if (supportFragmentManager.findFragmentByTag("detailFragment") == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.first_container_id, ListItemsFragment())
                .commit()
            supportFragmentManager.popBackStack()
        } else {
            //when we are back from spanned mode, we want to show the previous detail view
            supportFragmentManager.beginTransaction()
                .replace(R.id.first_container_id, DetailFragment(), "detailFragment")
                .addToBackStack("detailFragmentBackStack")
                .commit()
        }
    }

    //Here we will handle the back button added in the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}