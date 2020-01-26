package com.rhm.mysubmission03.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rhm.mysubmission03.R
import com.rhm.mysubmission03.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var verifyView: String //
    private lateinit var tvState: TextView //

    companion object {
        private const val STATE_RESULT = "state_result"  //
    }

    override fun onSaveInstanceState(outState: Bundle) {  //
        super.onSaveInstanceState(outState)  //
        outState.putString(STATE_RESULT, verifyView)  //
    }  //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

        tvState = findViewById(R.id.tv_state) //
        verifyView = tvState.text.toString()  //

        if (savedInstanceState != null) {  //
            val result = savedInstanceState.getString(STATE_RESULT) as String  //
            verifyView = result  //
        }  //

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
