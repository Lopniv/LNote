package com.android.lnote.feature.domain.use_case

import com.android.lnote.feature.domain.model.Note
import com.android.lnote.feature.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}