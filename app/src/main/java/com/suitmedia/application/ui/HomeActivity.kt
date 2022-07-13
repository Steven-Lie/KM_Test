package com.suitmedia.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.suitmedia.application.R
import com.suitmedia.application.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityHomeBinding
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_SELECTED = "extra_selected"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        activityHomeBinding.tvName.text = intent.getStringExtra(EXTRA_NAME)

        activityHomeBinding.btnChooseUser.setOnClickListener {
            val intent = Intent(this@HomeActivity, UserActivity::class.java)
            startActivity(intent)
        }

        activityHomeBinding.tvSelected.text = intent.getStringExtra(EXTRA_SELECTED)

        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.secondScreen) + "</font>")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}