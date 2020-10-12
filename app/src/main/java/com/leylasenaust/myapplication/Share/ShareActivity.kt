package com.leylasenaust.myapplication.Share

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.BottomNagViewHelper
import kotlinx.android.synthetic.main.activity_main.*

class ShareActivity : AppCompatActivity() {

    private val ACTIVITY_NO=4
    private val TAG="ShareActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        setupNagViews()
    }

    fun setupNagViews()
    {
        BottomNagViewHelper.setupBottomNagView(bottom_nag_view)
        BottomNagViewHelper.setupNag(this,bottom_nag_view)
        var menu=bottom_nag_view.menu
        var menuItem=menu.getItem(ACTIVITY_NO)
        menuItem.setChecked(true)
    }
}
