package com.souzaemerson.aluramvvm.data.database.repository

import androidx.lifecycle.LiveData
import com.souzaemerson.aluramvvm.data.model.Book

interface IDatabaseRepository {

    fun insertBook(book: Book)
    fun getAllBooks(): LiveData<List<Book>>
    fun removeBook(book: Book)
}