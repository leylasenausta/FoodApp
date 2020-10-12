package com.leylasenaust.myapplication.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.data.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.leylasenaust.myapplication.Models.Users

import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.EventBusDataEvent
import kotlinx.android.synthetic.main.kayit_fragment.*
import kotlinx.android.synthetic.main.kayit_fragment.view.*
import kotlinx.android.synthetic.main.kayit_fragment.view.buttonGir
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * A simple [Fragment] subclass.
 */
class KayitFragment : Fragment() {

      var telNo =""
      var verificationID=""
      var gelKod=""
      var comeMail=""
      var emailWithLogin=true
      lateinit var mAuth:FirebaseAuth
      lateinit var mRef:DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.kayit_fragment, container, false)

        mAuth= FirebaseAuth.getInstance()
        mRef=FirebaseDatabase.getInstance().reference

        if(mAuth.currentUser!=null)
        {
           mAuth.signOut()
        }

        view.textViewGrisYap.setOnClickListener {
            var intent=Intent(activity,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        view.TextNameSurnameKay.addTextChangedListener(watcher)
        view.TextPasswordKay.addTextChangedListener(watcher)
        view.TextUserNameKay.addTextChangedListener(watcher)

       view.buttonGir.setOnClickListener {

            if( emailWithLogin)
            {
                val passwords=view.TextPasswordKay.text.toString()
                val adSoyAd=view.TextNameSurnameKay.text.toString()
                val userNameAd=view.TextUserNameKay.text.toString()

                mAuth.createUserWithEmailAndPassword(comeMail,passwords)
                    .addOnCompleteListener { p0 ->
                        if (p0.isSuccessful) {

                            Toast.makeText(activity, "Welcome:)", Toast.LENGTH_SHORT).show()

                            val userID = mAuth.currentUser!!.uid.toString()

                            val kayOlKul =
                                Users(comeMail, passwords, userNameAd, adSoyAd, userID)

                            mRef.child("users").child(userID).setValue(kayOlKul)
                                .addOnCompleteListener { p1 ->
                                    if (p1.isSuccessful) {
                                        Toast.makeText(
                                            activity,
                                            "Welcome",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        Toast.makeText(
                                            activity,
                                            "Welcome",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }
                        } else {
                            Toast.makeText(
                                activity,
                                "No register" + p0.exception,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            ///Phone with in
            else{
                var passwords=view.TextPasswordKay.text.toString()
                var adSoyAd=view.TextNameSurnameKay.text.toString()
                var userNameAd=view.TextUserNameKay.text.toString()
                var fakeEmail= "$telNo@leyla.com"
                mAuth.createUserWithEmailAndPassword(fakeEmail,passwords)
                    .addOnCompleteListener {
                        OnCompleteListener<AuthResult> { p0 ->
                            if(p0!!.isSuccessful) {
                                Toast.makeText(activity, "Welcome :))" + mAuth.currentUser!!.uid, Toast.LENGTH_SHORT).show()

                                var userID = mAuth.currentUser!!.uid.toString()

                                var kayOlKul = Users(passwords,userNameAd,adSoyAd,telNo,fakeEmail,userID)


                                mRef.child("users").child(userID).setValue(kayOlKul)
                                    .addOnCompleteListener { p0 ->
                                        if (p0.isSuccessful) { Toast.makeText(activity, "Yes Login", Toast.LENGTH_SHORT).show()
                                        }
                                        else {
                                            Toast.makeText(activity, "Login Delete", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            }
                            else{
                                Toast.makeText(activity,"No have login"+ p0.exception,Toast.LENGTH_SHORT).show() }
                        }
                    }
            }
        }

        return view
    }

     var watcher : TextWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s!!.length> 5){

                if(TextNameSurnameKay.text.toString().length>5 && TextPasswordKay.text.toString().length>5 && TextUserNameKay.text.toString().length>5){
                    buttonGir.isEnabled=true
                    buttonGir.setBackgroundColor(ContextCompat.getColor(activity!!,R.color.colorOrange))
                    buttonGir.setTextColor(ContextCompat.getColor(activity!!,R.color.colorWhite))
                }
                else {
                    buttonGir.isEnabled=false
                    buttonGir.setTextColor(ContextCompat.getColor(activity!!,R.color.colorKap))
                    buttonGir.setBackgroundResource(R.color.colorWhite)
                }
            }
            else{
                  buttonGir.isEnabled=false
                  buttonGir.setTextColor(ContextCompat.getColor(activity!!,R.color.colorKap))
                  buttonGir.setBackgroundResource(R.color.colorWhite)
            }
        }

    }




    ///////////////////////////////////////////////EVENT BUS/////////////////////////////////
    @Subscribe(sticky = true)
    internal fun onRecordInformation(userInformation: EventBusDataEvent.InformationUserSend){

        if(userInformation.emailSign)
        {
             emailWithLogin=true
             comeMail=userInformation.email!!
            Log.e("leyla", "Gelen Tel No$comeMail")

            Toast.makeText(activity, "Come to Mail$comeMail", Toast.LENGTH_SHORT).show()
        }
        else{
               emailWithLogin=false
            telNo=userInformation.telNo!!
            verificationID=userInformation.verificationID!!
            gelKod=userInformation.code!!

            Toast.makeText(activity,"ge kod : "+gelKod+"verificationId" +verificationID, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }
   ///////////////////////////////////////////////EVENT BUS/////////////////////////////////
}
