package com.leylasenaust.myapplication.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.BottomNagViewHelper
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.boottoom_Nagivation_View_setting
import kotlinx.android.synthetic.main.activity_profile_setting.*


class ProfileSettingActivity : AppCompatActivity() {

    private val ACTIVITY_NO=2
    private val TAG="ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting)
        setupToolBar()
        setupNagViews()
        fragmentNagivationss()
    }
    fun setupNagViews()
    {
        BottomNagViewHelper.setupBottomNagView(boottoom_Nagivation_View_setting)
        BottomNagViewHelper.setupNag(this,boottoom_Nagivation_View_setting)
        var menu=boottoom_Nagivation_View_setting.menu
        var menuItem=menu.getItem(ACTIVITY_NO)
        menuItem.setChecked(true)
    }

    private fun setupToolBar()
    {
        imageView_back.setOnClickListener {
            var intent=Intent(this,ProfileActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

    }

    private fun fragmentNagivationss()
    {
        textView_düzenle_hesap_ayari.setOnClickListener {
            profileSettingRoots.visibility=View.GONE
            var transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.profileSettingContainer,ProfileEditFragment())
            transaction.addToBackStack("ProfileEditEklendi")
            transaction.commit()
        }

        textView20.setOnClickListener {
            profileSettingRoots.visibility=View.GONE
            var transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.profileSettingContainer,SignOutFragment())
            transaction.addToBackStack("signOutEklendi")
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        profileSettingRoots.visibility=View.VISIBLE
        super.onBackPressed()
    }
}
