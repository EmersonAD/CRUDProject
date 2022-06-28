package com.souzaemerson.aluramvvm.ui.activity.fragment.home.viewmodel

import androidx.lifecycle.*
import com.souzaemerson.aluramvvm.core.State
import com.souzaemerson.aluramvvm.data.database.repository.IDatabaseRepository
import com.souzaemerson.aluramvvm.data.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class HomeViewModel(
    private val repository: IDatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun getBooks() = repository.getAllBooks()

    class HomeViewModelProviderFactory(
        private val repository: IDatabaseRepository,
        private val ioDispatcher: CoroutineDispatcher
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repository, ioDispatcher) as T
            }
            throw IllegalArgumentException("Wrong viewModel Class")
        }

    }
}