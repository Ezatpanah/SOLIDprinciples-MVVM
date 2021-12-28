package com.ezatpanah.core.data

data class NoteModel(
    var title :String,
    var content :String,
    var creationTime:Long,
    var updateTime:Long,
    var id : Long=0
)