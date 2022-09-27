package com.android.lnote.feature.persentation.home

import com.android.lnote.feature.domain.model.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
}
