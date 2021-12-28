package com.ezatpanah.core.usecase

import com.ezatpanah.core.data.NoteModel
import com.ezatpanah.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteModel: NoteModel) = noteRepository.addNote(noteModel)
}