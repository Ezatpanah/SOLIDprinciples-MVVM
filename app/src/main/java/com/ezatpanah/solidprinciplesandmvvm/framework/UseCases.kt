package com.ezatpanah.solidprinciplesandmvvm.framework

import com.ezatpanah.core.usecase.AddNote
import com.ezatpanah.core.usecase.GetAllNotes
import com.ezatpanah.core.usecase.GetNote
import com.ezatpanah.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)