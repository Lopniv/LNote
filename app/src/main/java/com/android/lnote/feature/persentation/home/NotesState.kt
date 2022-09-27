package com.android.lnote.feature.persentation.home

import com.android.lnote.feature.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)
