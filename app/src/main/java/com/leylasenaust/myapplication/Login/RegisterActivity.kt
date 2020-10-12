package com.leylasenaust.myapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.leylasenaust.myapplication.R
import com.leylasenaust.myapplication.utils.EventBusDataEvent
import kotlinx.android.synthetic.main.activity_register.*
import org.greenrobot.eventbus.EventBus

class RegisterActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    lateinit var manager:FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
         manager=supportFragmentManager
        manager.addOnBackStackChangedListener(this)
        init()
    }

  fun init(){

     textViewGrisYap.setOnClickListener {
         var intent=Intent(this@RegisterActivity,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
         startActivity(intent)
     }
        textViewPosta.setOnClickListener {

            viewTelephoneInce.visibility=View.INVISIBLE
            viewPostInce.visibility=View.VISIBLE
            editTextTelefonRegister.setText("")
            editTextTelefonRegister.inputType=InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            editTextTelefonRegister.hint = "E-Posta"
            buttonRegister.isEnabled=false
        }

        textViewTelefon.setOnClickListener {
            viewTelephoneInce.visibility=View.VISIBLE
            viewPostInce.visibility=View.INVISIBLE
            editTextTelefonRegister.setText("")
            editTextTelefonRegister.inputType=InputType.TYPE_CLASS_PHONE
            editTextTelefonRegister.hint = "Telefon"
            buttonRegister.isEnabled=false
        }

        editTextTelefonRegister.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               if(s!!.length>=10)
               {
                   buttonRegister.isEnabled=true
                   buttonRegister.setTextColor(ContextCompat.getColor(this@RegisterActivity,R.color.colorGreys))
                   buttonRegister.setBackgroundColor(ContextCompat.getColor(this@RegisterActivity,R.color.colorOrange))
               }

                else
               {
                   buttonRegister.isEnabled=true
                   buttonRegister.setTextColor(ContextCompat.getColor(this@RegisterActivity,R.color.colorIceGrey))
                   buttonRegister.setBackgroundColor(ContextCompat.getColor(this@RegisterActivity,R.color.colorIceBlue))
               }
            }
        })

        buttonRegister.setOnClickListener {
            if(editTextTelefonRegister.hint.toString() == "Telefon"){

                if(isValidPhone(editTextTelefonRegister.text.toString())){
                    loginRoots.visibility=View.GONE
                    loginContainer.visibility=View.VISIBLE

                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.loginContainer,PhoneCodeInFragment())
                    transaction.addToBackStack("PhoneCodeAdded")
                    transaction.commit()
                    EventBus.getDefault().postSticky(EventBusDataEvent.InformationUserSend(editTextTelefonRegister.text.toString(),null,null,null,false))
                }
                else{
                    Toast.makeText(this, "Lütfen Geçerli Bir Telefon giriniz" , Toast.LENGTH_SHORT).show()
                }
            }

            else
            {
                if(isValidEmail(editTextTelefonRegister.text.toString())){
                    loginRoots.visibility=View.GONE
                    loginContainer.visibility=View.VISIBLE
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.loginContainer,KayitFragment())
                    transaction.addToBackStack("EmailFragmentAdded")
                    transaction.commit()
                    EventBus.getDefault().postSticky(EventBusDataEvent.InformationUserSend(null,editTextTelefonRegister.text.toString(),null,null,true))
                }
                else{
                    Toast.makeText(this, "Lütfen Geçerli Bir Email giriniz" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBackStackChanged() {
        val personalNumber=manager.backStackEntryCount
           if(personalNumber==0)
           {
               loginRoots.visibility=View.VISIBLE
           }
    }

    fun isValidEmail(controlEmails:String):Boolean
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(controlEmails).matches()
    }

   fun isValidPhone(controlPhones:String):Boolean
    {
        if(controlPhones.length >14)
        {
            return false
        }
        return android.util.Patterns.PHONE.matcher(controlPhones).matches()
    }
}

