package com.leylasenaust.myapplication.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.BottomNagViewHelper
import com.leylasenaust.myapplication.utils.MainPagerAdopter
import com.leylasenaust.myapplication.utils.UniversalImageLoader
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val ACTIVITY_NO=0
    private val TAG="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initImageLoader()
        setupNagViews()
        setupMainViewPager()
    }

    private fun setupNagViews()
    {
        BottomNagViewHelper.setupBottomNagView(bottom_nag_view)
        BottomNagViewHelper.setupNag(this,bottom_nag_view)
        var menu=bottom_nag_view.menu
        var menuItem=menu.getItem(ACTIVITY_NO)
        menuItem.isChecked = true
    }


     private fun setupMainViewPager()
    {
        val mainPagerAdopter=MainPagerAdopter(supportFragmentManager)
        mainPagerAdopter.addFragment(CameraFragment())
        mainPagerAdopter.addFragment((MainFragment()))
        mainPagerAdopter.addFragment(MessageFragment())

        //activity main de bulunan view pagera ooluşturdugumuz adaptörü atadık....
        home_view_pager.adapter=mainPagerAdopter

        //view pagerin main fragmenti ile başlamasını sağladık.......
        home_view_pager.currentItem = 1

    }
    //private fun InıtImageLoader()
    //{
        //var UniversalImageLoader= activity?.let { UniversalImageLoader(it) }
     // }
    private fun initImageLoader()
    {

        val universalImageLoader= this.let { UniversalImageLoader(this) }
        ImageLoader.getInstance().init(universalImageLoader.config)
    }
}
