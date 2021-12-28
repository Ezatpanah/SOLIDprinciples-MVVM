package com.ezatpanah.core.repository

import com.ezatpanah.core.data.NoteModel

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(noteModel:NoteModel) = dataSource.add(noteModel)

    suspend fun getNote(id:Long)=dataSource.get(id)

    suspend fun getAllNote()=dataSource.getAll()

    suspend fun removeNote(noteModel: NoteModel)=dataSource.remove(noteModel)

}