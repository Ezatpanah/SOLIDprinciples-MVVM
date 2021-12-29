package com.ezatpanah.core.usecase

import com.ezatpanah.core.data.NoteModel

class GetWordCount {
    operator fun  invoke(noteModel: NoteModel)= getCount(noteModel.title) + getCount(noteModel.content)

    private fun getCount(str :String)=
        str.split(
            " ",
            "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()

}