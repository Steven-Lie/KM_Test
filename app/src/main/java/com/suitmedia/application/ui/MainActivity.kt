package com.suitmedia.application.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.application.R
import com.suitmedia.application.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportActionBar?.hide()

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        activityMainBinding.btnCheck.setOnClickListener {
            val palindrome = activityMainBinding.edPalindrome.text.toString()
            if (palindrome.isEmpty()) {
                activityMainBinding.edPalindrome.error = "Masih Kosong"
            } else {
                isPalindrome(palindrome)
            }
        }

        activityMainBinding.btnNext.setOnClickListener {
            val name = activityMainBinding.edName.text.toString()
            if (name.isEmpty()) {
                activityMainBinding.edName.error = "Masih Kosong"
            } else {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra(HomeActivity.EXTRA_NAME, name)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String) {
        var isPalindrome = true
        val checkingPalindrome = text.lowercase().trim().replace("\\s".toRegex(), "")
        val lastIndex = checkingPalindrome.length - 1
        for (i in 0 until checkingPalindrome.length / 2) {
            if (checkingPalindrome[i] != checkingPalindrome[lastIndex - i]) {
                isPalindrome = false
                break
            }
        }
        showDialog(isPalindrome, text)
    }

    private fun showDialog(palindromeCheckResult: Boolean, text: String) {
        val showText = if (palindromeCheckResult) {
            getString(R.string.isPalindrome, text)
        } else {
            getString(R.string.isNotPalindrome, text)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(R.string.result)
            setMessage(showText)
            setNeutralButton(getString(R.string.ok)) { _, _ -> }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}