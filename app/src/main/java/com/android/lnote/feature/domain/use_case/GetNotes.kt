package com.android.lnote.feature.domain.use_case

import com.android.lnote.feature.domain.model.Note
import com.android.lnote.feature.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}