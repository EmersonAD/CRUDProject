package com.souzaemerson.aluramvvm.ui.activity.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.souzaemerson.aluramvvm.R
import com.souzaemerson.aluramvvm.data.database.AppDatabase
import com.souzaemerson.aluramvvm.data.database.repository.DatabaseRepositoryImpl
import com.souzaemerson.aluramvvm.data.database.repository.IDatabaseRepository
import com.souzaemerson.aluramvvm.data.model.Book
import com.souzaemerson.aluramvvm.databinding.FragmentHomeBinding
import com.souzaemerson.aluramvvm.ui.activity.fragment.home.adapter.HomeAdapter
import com.souzaemerson.aluramvvm.ui.activity.fragment.home.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: IDatabaseRepository

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: HomeAdapter

    private val dao by lazy {
        AppDatabase.getDatabase(requireContext()).bookDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = DatabaseRepositoryImpl(dao = dao)
        viewModel = HomeViewModel.HomeViewModelProviderFactory(repository, Dispatchers.IO)
            .create(HomeViewModel::class.java)

        observeVMEvents()

        binding.homeFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formFragment)
        }
    }

    private fun observeVMEvents() {
        viewModel.getBooks().observe(viewLifecycleOwner) { listBooks ->
            when {
                listBooks.isNotEmpty() -> {
                    setRecycler(listBooks)
                }
            }
        }
    }

    private fun setAdapter(list: List<Book>) {
        mAdapter = HomeAdapter(list) {

        }
    }

    private fun setRecycler(list: List<Book>) {
        setAdapter(list)
        binding.homeRecyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

}