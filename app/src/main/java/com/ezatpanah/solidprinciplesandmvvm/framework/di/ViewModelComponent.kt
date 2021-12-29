package com.ezatpanah.solidprinciplesandmvvm.framework.di

import com.ezatpanah.solidprinciplesandmvvm.framework.ListViewModel
import com.ezatpanah.solidprinciplesandmvvm.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class,RepositoryModule::class,UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}