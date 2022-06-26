package com.souzaemerson.aluramvvm.data.database.repository

import androidx.lifecycle.LiveData
import com.souzaemerson.aluramvvm.data.model.Book

interface IDatabaseRepository {

    suspend fun insertBook(book: Book)
    fun getAllBooks(): LiveData<List<Book>>
    suspend fun removeBook(book: Book)
}