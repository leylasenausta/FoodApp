package com.leylasenaust.myapplication.utils

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.leylasenaust.myapplication.Home.MainActivity
import com.leylasenaust.myapplication.Like.LikeActivity
import com.leylasenaust.myapplication.Profile.ProfileActivity
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.Search.SearchActivity
import com.leylasenaust.myapplication.Share.ShareActivity

class BottomNagViewHelper {

         companion object
        {
            fun setupBottomNagView(bottomNavigationViewEx: BottomNavigationViewEx)
            {
                bottomNavigationViewEx.enableAnimation(false)
                bottomNavigationViewEx.enableItemShiftingMode(false)
                bottomNavigationViewEx.enableShiftingMode(false)
                bottomNavigationViewEx.setTextVisibility(false)
            }

            fun setupNag(context: Context, bottomNavigationViewEx: BottomNavigationViewEx)
            {
                bottomNavigationViewEx.onNavigationItemSelectedListener=object :BottomNavigationView.OnNavigationItemSelectedListener
                {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {

                        when(item.itemId)
                        {
                            R.id.ic_home ->
                        {
                          val intent= Intent(context, MainActivity::class.java ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true
                        }
                            //----------------------------

                            R.id.ic_like ->
                            {
                                val intent= Intent(context, LikeActivity::class.java ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                               return true
                            }
                            //------------------------------
                            R.id.ic_profile ->
                            {
                                val intent= Intent(context, ProfileActivity::class.java ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            //------------------------------
                            R.id.ic_search ->
                            {
                                val intent= Intent(context, SearchActivity::class.java ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            //----------------------------
                            R.id.ic_share ->
                            {
                                val intent= Intent(context, ShareActivity::class.java ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                 return true
                            }

                        }
                        return false
                    }

                }
            }
        }
    }
