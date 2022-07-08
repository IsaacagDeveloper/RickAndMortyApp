package com.mango.android.presentation.features.characterslist.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mango.android.presentation.R
import com.mango.android.presentation.databinding.ItemCharactersListBinding
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel

class CharactersListViewHolder (private val binding: ItemCharactersListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterUIModel) {
        binding.characterNameTextView.text = item.name
        binding.characterSpecieTextView.text = item.species
        binding.characterStatusTextView.text = item.status

        val genderImage = if (item.gender == "Female") {
            R.drawable.ic_woman
        } else {
            R.drawable.ic_man
        }

        binding.characterGenderImageView.clipToOutline = true
        binding.characterGenderImageView.setImageResource(genderImage)

        if (item.imageUrl.isNotEmpty()) {
            Glide.with(binding.root)
                .load(item.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .into(binding.characterImageView)
        }
    }

}