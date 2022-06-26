package com.souzaemerson.aluramvvm.ui.activity.fragment.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.souzaemerson.aluramvvm.R
import com.souzaemerson.aluramvvm.data.database.AppDatabase
import com.souzaemerson.aluramvvm.data.database.repository.DatabaseRepositoryImpl
import com.souzaemerson.aluramvvm.data.database.repository.IDatabaseRepository
import com.souzaemerson.aluramvvm.data.model.Book
import com.souzaemerson.aluramvvm.databinding.FragmentFormBinding
import com.souzaemerson.aluramvvm.ui.activity.fragment.form.viewmodel.FormViewModel
import kotlinx.coroutines.Dispatchers


class FormFragment() : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private lateinit var viewModel: FormViewModel
    private lateinit var repository: IDatabaseRepository
    private val dao by lazy {
        AppDatabase.getDatabase(requireContext()).bookDao()
    }
    private var bookId:Long = 0
    private lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = DatabaseRepositoryImpl(dao)
        viewModel = FormViewModel.FormViewModelProviderFactory(repository, Dispatchers.IO)
            .create(FormViewModel::class.java)

        binding.formButton.setOnClickListener {
            binding.run {
                val title = formEditTitle.text.toString()
                val description = formEditDescription.text.toString()
                val image = null
                book = viewModel.setBook(bookId,
                    title = title,
                    description = description,
                    image = image)
                viewModel.insertBook(book)
            }
        }

    }
}