package com.suitmedia.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.application.R
import com.suitmedia.application.data.UserAdapter
import com.suitmedia.application.data.response.DataItem
import com.suitmedia.application.databinding.ActivityHomeBinding
import com.suitmedia.application.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var activityUserBinding: ActivityUserBinding
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(activityUserBinding.root)

        userViewModel.getUser()

        userViewModel.listUsers.observe(this) {
            showUsers(it)
        }

        userViewModel.isLoading.observe(this) {
            activityUserBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.thirdScreen) + "</font>")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
    }

    private fun showUsers(users: List<DataItem>) {
        activityUserBinding.rvUsers.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(users)
        activityUserBinding.rvUsers.adapter = userAdapter
        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val intent = Intent(this@UserActivity, HomeActivity::class.java)
                intent.putExtra(HomeActivity.EXTRA_SELECTED, "${data.firstName} ${data.lastName}")
                startActivity(intent)
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}