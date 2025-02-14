package com.mango.android.presentation.features.characterslist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mango.android.presentation.databinding.ItemCharactersListBinding
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel

private const val ITEMS_LIST_TO_CHECK_IF_USER_IS_CLOSE_TO_THE_END_OF_LIST = 5

class CharactersListAdapter(
    private val characterClickListener: ((CharacterUIModel) -> Unit),
    private val closeLastItemListener: (() -> Unit)
) : ListAdapter<CharacterUIModel, CharactersListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharacterUIModel>() {
            override fun areItemsTheSame(oldItem: CharacterUIModel, newItem: CharacterUIModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterUIModel, newItem: CharacterUIModel): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val binding = ItemCharactersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)){
                bind(this)
                itemView.setOnClickListener { characterClickListener.invoke(this) }
                if ((currentList.size - ITEMS_LIST_TO_CHECK_IF_USER_IS_CLOSE_TO_THE_END_OF_LIST) == position) {
                    closeLastItemListener.invoke()
                }
            }
        }
    }

}