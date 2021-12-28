package com.ezatpanah.core.usecase

import com.ezatpanah.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id : Long)=noteRepository.getNote(id)
}