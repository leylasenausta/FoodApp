package com.leylasenaust.myapplication.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.EventBusDataEvent
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.fragment_phone_code_in.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class PhoneCodeInFragment : Fragment() {

    var comePhone=""
    lateinit var mcallbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationID=""
    var comeCodE=""
    lateinit var progressBars:ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_phone_code_in, container, false)
        view.textVieUserNo.setText(comePhone)

        progressBars=view.progressBarPhoneIn

        setupCallBack()
        view.buttonPhoneCode.setOnClickListener {

            if(comeCodE.equals(view.editTextOnyCode.text.toString()))
            {
                EventBus.getDefault().postSticky(EventBusDataEvent.InformationUserSend(comePhone,null,verificationID , comeCodE, false))
                var transaction=activity!!.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.loginContainer,KayitFragment())
                transaction.addToBackStack("KayıtFragmentiOluşturuldu")
                transaction.commit()
            }
            else{
                Toast.makeText(activity,"Hatalı Giriş Yaptınız",Toast.LENGTH_SHORT).show()
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            comePhone, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this.activity!!, // Activity (for callback binding)
            mcallbacks) // OnVerificationStateChangedCallbacks

        return view
    }
    @Subscribe (sticky = true)
    internal fun onPhoneNoEvent(userInformation:EventBusDataEvent.InformationUserSend){
         comePhone= userInformation.telNo!!
        Log.e("leyla", "comeOn" +comePhone)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

   fun setupCallBack(){
        mcallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
              if(!credential.smsCode.isNullOrEmpty())
                comeCodE=credential.smsCode!!
                progressBars.visibility=View.INVISIBLE
            }
            override fun onVerificationFailed(e: FirebaseException) {
             Log.e("HATA","Hata çıktı"+e.message)
                progressBars.visibility=View.INVISIBLE
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                verificationID= verificationId
                progressBars.visibility=View.VISIBLE
            }
        }
    }
}
