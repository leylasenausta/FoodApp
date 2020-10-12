package com.leylasenaust.myapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.leylasenaust.myapplication.Models.Users
import com.leylasenaust.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_profile_edit.*

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var mRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()
        mRef=FirebaseDatabase.getInstance().reference

        init()

    }


    fun init(){

       editText2Begin.addTextChangedListener(watcher)
       editText7.addTextChangedListener(watcher)


       buttonLoginn.setOnClickListener {
        loginUserCheck(editText2Begin.text.toString(),editText.text.toString())
       }
    }

     fun loginUserCheck(emailPhoneNumberUserName: String, passwords: String) {
      mRef.child("users").orderByChild("email").addListenerForSingleValueEvent(object : ValueEventListener{
          override fun onCancelled(p0: DatabaseError) {

          }

          override fun onDataChange(p0: DataSnapshot) {
            for(ds in p0.children){

                var readUsers=ds.getValue(Users::class.java)

                if(readUsers!!.email!!.toString() == emailPhoneNumberUserName){

                    loginAc(readUsers,passwords,false)
                    break
                }

                else if(readUsers!!.userName!!.toString() == emailPhoneNumberUserName){
                    loginAc(readUsers,passwords,false)
                    break
                }

                else if(readUsers!!.phoneNumber!!.toString() == emailPhoneNumberUserName){
                    loginAc(readUsers,passwords,true)
                    break
                }
            }
          }

      })
    }

     fun loginAc(readUsers: Users, passwords: String, phoneWithGir: Boolean) {
      var loginYapEmail=""

        if (phoneWithGir) {
            loginYapEmail=readUsers.emailPhoneNumber.toString()
        }
        else{
            loginYapEmail=readUsers.email.toString()
        }

        mAuth.signInWithEmailAndPassword(loginYapEmail,passwords)
            .addOnCompleteListener { p0 ->
                if(p0.isSuccessful){
                    Toast.makeText(this@LoginActivity,"Oturum Açıldı :"+mAuth.currentUser!!.uid, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@LoginActivity,"Kullanıcı Adı/Şifre Hatalı :", Toast.LENGTH_SHORT).show()
                }
            }
     }

     var watcher: TextWatcher=object :TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
           if(editText2Begin.text.toString().length>=6 && editText7.text.toString().length>=6){

               buttonLoginn.isEnabled=true
               buttonLoginn.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.colorWhite))
               buttonLoginn.setBackgroundResource(R.color.colorOrange)
           }
            else{
               buttonLoginn.isEnabled=false
               buttonLoginn.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.colorPrimaryDark))
               buttonLoginn.setBackgroundResource(R.color.colorWhite)
           }
        }

    }
}
