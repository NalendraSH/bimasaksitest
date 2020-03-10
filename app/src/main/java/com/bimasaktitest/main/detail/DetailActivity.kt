package com.bimasaktitest.main.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bimasaktitest.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setNavBack()

        val jsonData = intent.getStringExtra("detail_json")!!
        if (jsonData != "") {
            val formattedJson = jsonData.replace(",", "\n").replace("{", "")
                .replace("}", "").replace(":", ": ").replace("\"", "")
            textview_detail.text = formattedJson
        } else {
            textview_detail.text = "Data Kosong"
        }
    }

    private fun setNavBack() {
        toolbar_detail.setNavigationIcon(R.drawable.ic_nav_back)
        toolbar_detail.setNavigationOnClickListener { finish() }
    }
}
