package com.example.task.Model

data class ImageResponse(
    val urls : UrlModel?=null,
    val user : UserModel?=null,
    var edtDescription:String?=null
)

data class UrlModel(
    val regular : String
)

data class UserModel(
    val bio : String
)
