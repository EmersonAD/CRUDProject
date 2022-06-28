package com.souzaemerson.aluramvvm.data.database.repository

import androidx.lifecycle.LiveData
import com.souzaemerson.aluramvvm.data.database.DatabaseDAO
import com.souzaemerson.aluramvvm.data.model.Book

class DatabaseRepositoryImpl(private val dao: DatabaseDAO): IDatabaseRepository {
    override fun insertBook(book: Book){
        dao.insertBook(book)
    }

    override fun getAllBooks(): LiveData<List<Book>> {
       return dao.getAllBooks()
    }

    override fun removeBook(book: Book){
        dao.deleteBook(book)
    }
}