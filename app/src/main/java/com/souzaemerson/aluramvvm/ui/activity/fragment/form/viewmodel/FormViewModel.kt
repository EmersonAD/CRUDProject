package com.souzaemerson.aluramvvm.ui.activity.fragment.form.viewmodel

import androidx.lifecycle.*
import com.souzaemerson.aluramvvm.core.State
import com.souzaemerson.aluramvvm.data.database.repository.IDatabaseRepository
import com.souzaemerson.aluramvvm.data.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class FormViewModel(
    private val repository: IDatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _response = MutableLiveData<State<Boolean>>()
    val response: LiveData<State<Boolean>> = _response

    fun insertBook(book: Book){
        viewModelScope.launch {
            try {
                withContext(ioDispatcher){
                    repository.insertBook(book)
                }
                _response.value = State.success(true)
            }catch (error: Throwable){
                _response.value = State.error(error)
            }
        }
    }

    fun setBook(
        movieId:Long = 0,
        image: String?,
        title: String,
        description: String): Book{

        return Book(movieId, title, description, image)
    }

    class FormViewModelProviderFactory(
        private val repository: IDatabaseRepository,
        private val ioDispatcher: CoroutineDispatcher
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FormViewModel::class.java)){
                return FormViewModel(repository, ioDispatcher) as T
            }
            throw IllegalArgumentException("Unknown viewModel Class")
        }

    }
}