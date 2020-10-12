package com.leylasenaust.myapplication.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.BottomNagViewHelper
import com.leylasenaust.myapplication.utils.UniversalImageLoader
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.circle_profile_image_profile
import kotlinx.android.synthetic.main.fragment_profile_edit.*


class ProfileActivity : AppCompatActivity() {

    private val ACTIVITY_NO=2
    private val TAG="ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupProfilePhoto()
        setupToolBar()
        setupNagViews()
    }

    private fun setupProfilePhoto(){
        val imgUrl="cdn.icon-icons.com/icons2/729/PNG/512/android_icon-icons.com_62719.png"
        UniversalImageLoader.setImage(imgUrl,circle_profile_image_profile,null, "https://")
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
        imageprofilesetting.setOnClickListener{
            var intent=Intent(this,ProfileSettingActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        textView10.setOnClickListener {
            pprofileRoot.visibility=View.GONE
            var transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.ppRofileContainer,ProfileEditFragment())
            transaction.addToBackStack("")
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        pprofileRoot.visibility=View.VISIBLE
        super.onBackPressed()
    }


}
