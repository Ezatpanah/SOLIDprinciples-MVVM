package com.ezatpanah.solidprinciplesandmvvm.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezatpanah.core.data.NoteModel

@Entity(tableName = "note")
data class NoteEntity(
    var title :String,
    var content :String,

    @ColumnInfo(name = "creation_time")
    var creationTime:Long,

    @ColumnInfo(name = "update_time")
    val updateTime : Long,

    @PrimaryKey(autoGenerate = true)
    var id : Long =0L
){
    companion object{
        fun fromNote(note :NoteModel) = NoteEntity(note.title,note.content,note.creationTime,note.updateTime,note.id)
    }

    fun toNote()=NoteModel(title,content,creationTime,updateTime,id)

}
