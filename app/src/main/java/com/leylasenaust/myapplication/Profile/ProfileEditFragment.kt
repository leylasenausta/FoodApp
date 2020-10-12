package com.leylasenaust.myapplication.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.UniversalImageLoader
import com.nostra13.universalimageloader.core.ImageLoader
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*
import kotlinx.android.synthetic.main.fragment_profile_edit.view.circle_profile_image_profile

/**
 * A simple [Fragment] subclass.
 */
class ProfileEditFragment : Fragment() {

    lateinit var circleProfileImageView:CircleImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_profile_edit, container, false)
        circleProfileImageView=view.findViewById(R.id.circle_profile_image_profile)
        setupProfilePicture()


        view.imageView2.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }


   private fun setupProfilePicture() {

       var imgUrl="cdn.icon-icons.com/icons2/729/PNG/512/android_icon-icons.com_62719.png"
       UniversalImageLoader.setImage(imgUrl,circleProfileImageView,null,"https://")
    }
}
