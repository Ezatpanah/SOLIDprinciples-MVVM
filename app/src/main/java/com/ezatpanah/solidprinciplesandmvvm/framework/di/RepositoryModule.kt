package com.ezatpanah.solidprinciplesandmvvm.framework.di

import android.app.Application
import com.ezatpanah.core.repository.NoteRepository
import com.ezatpanah.solidprinciplesandmvvm.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app:Application) = NoteRepository(RoomNoteDataSource(app))
}