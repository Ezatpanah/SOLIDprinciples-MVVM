package com.ezatpanah.core.usecase

import com.ezatpanah.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke()=noteRepository.getAllNote()
}