package com.souzaemerson.aluramvvm.ui.activity.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.souzaemerson.aluramvvm.data.model.Book
import com.souzaemerson.aluramvvm.databinding.ItemBinding

class HomeAdapter(
    private val listBook: List<Book>,
    private val itemClick: ((item: Book) -> Unit)
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = listBook[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int = listBook.count()

    class HomeViewHolder(
        private val binding: ItemBinding,
        private val itemClick: (item: Book) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var book: Book

        fun bindView(book: Book) {

            this.book = book
            val title = binding.itemTitle
            title.text = book.title
            val description = binding.itemDescription
            description.text = book.description
            binding.itemImage.load(book.image)

            itemView.setOnClickListener {
                itemClick.invoke(book)
            }
        }
    }
}