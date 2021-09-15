package com.mobile.fair.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.fair.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dashboard_activity)

    }
}
