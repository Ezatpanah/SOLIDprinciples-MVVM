package com.ezatpanah.core.repository

import com.ezatpanah.core.data.NoteModel

interface NoteDataSource {

    suspend fun add(noteModel: NoteModel)

    suspend fun get(id: Long): NoteModel?

    suspend fun getAll(): List<NoteModel>

    suspend fun remove(noteModel: NoteModel)

}
