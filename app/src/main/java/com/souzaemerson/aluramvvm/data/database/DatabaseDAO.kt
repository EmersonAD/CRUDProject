package com.souzaemerson.aluramvvm.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy
import com.souzaemerson.aluramvvm.data.model.Book

@Dao
interface DatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): LiveData<List<Book>>

    @Delete
    suspend fun deleteBook(book: Book)
}