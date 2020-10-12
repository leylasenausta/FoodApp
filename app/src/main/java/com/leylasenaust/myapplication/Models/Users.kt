package com.leylasenaust.myapplication.Models

class Users {
     var email: String? = null
     var password: String? = null
     var userName: String? = null
     var nameSurname: String? = null
     var phoneNumber: String? = null
     var emailPhoneNumber: String? = null
     var userId: String? = null

    constructor(
        email: String?,
        password: String?,
        userName: String?,
        nameSurname: String?,
        userId:String?
    ) {
        this.email = email
        this.password = password
        this.userName = userName
        this.nameSurname = nameSurname
        this.userId = userId
    }

    constructor(
        password: String?,
        userName: String?,
        nameSurname: String?,
        phoneNumber: String?,
        emailPhoneNumber: String?,
        userId:String?
    ) {
        this.password = password
        this.userName = userName
        this.nameSurname = nameSurname
        this.phoneNumber = phoneNumber
        this.emailPhoneNumber = emailPhoneNumber
        this.userId = userId
    }
}