package com.leylasenaust.myapplication.utils

import androidx.appcompat.widget.DialogTitle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MainPagerAdopter(fm:FragmentManager) :FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private var mFragmentList:ArrayList<Fragment> =ArrayList()

   override fun getItem(position: Int): Fragment {

    return mFragmentList[position]
    }

  override fun getCount(): Int {

       return mFragmentList.size
  }


    //personal function
    fun addFragment(fragment: Fragment)
    {
        mFragmentList.add(fragment)

    }

}