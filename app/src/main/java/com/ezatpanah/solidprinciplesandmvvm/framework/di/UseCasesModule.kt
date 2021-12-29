package com.ezatpanah.solidprinciplesandmvvm.framework.di

import com.ezatpanah.core.repository.NoteRepository
import com.ezatpanah.core.usecase.*
import com.ezatpanah.solidprinciplesandmvvm.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )

}