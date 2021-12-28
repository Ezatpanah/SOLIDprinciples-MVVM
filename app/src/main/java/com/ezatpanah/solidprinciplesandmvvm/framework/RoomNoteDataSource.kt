package com.ezatpanah.solidprinciplesandmvvm.framework

import android.content.Context
import com.ezatpanah.core.data.NoteModel
import com.ezatpanah.core.repository.NoteDataSource
import com.ezatpanah.solidprinciplesandmvvm.framework.db.DatabaseService
import com.ezatpanah.solidprinciplesandmvvm.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDataSource {

    val noteDao =DatabaseService.getInstance(context).noteDao()

    override suspend fun add(noteModel: NoteModel) =noteDao.addNoteEntity(NoteEntity.fromNote(noteModel))

    override suspend fun get(id: Long)=noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll()=noteDao.getAllNoteEntity().map { it.toNote() }

    override suspend fun remove(noteModel: NoteModel) =noteDao.deleteNoteEntity(NoteEntity.fromNote(noteModel))

}