package com.mango.android.presentation.features.characterdetail.view

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mango.android.core.core.Failure
import com.mango.android.domain.models.Character
import com.mango.android.presentation.R
import com.mango.android.presentation.databinding.FragmentCharacterDetailBinding
import com.mango.android.presentation.extensions.*
import com.mango.android.presentation.features.characterdetail.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    // VIEW MODEL
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    //VIEW BINDING
    private var _fragmentCharacterDetailBinding: FragmentCharacterDetailBinding? = null
    private val fragmentCharacterDetailBinding get() = _fragmentCharacterDetailBinding!!

    //SAFE ARGS
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(characterDetailViewModel) {
            observe(characterDetailInfoLiveData, ::renderCharacter)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterDetailBinding.bind(view)
        _fragmentCharacterDetailBinding = binding

        initializeNavigation()
        loadCharacterDetailInformation()
    }

    private fun initializeNavigation() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
    }

    private fun goBack() {
        this.findNavController().popBackStack()
    }

    private fun loadCharacterDetailInformation() {
        fragmentCharacterDetailBinding.progressBar.visible()
        characterDetailViewModel.userRequireDetailInformationOfCharacter(args.characterIDSelected)
    }

    private fun renderCharacter(character: Character?) {
        fragmentCharacterDetailBinding.progressBar.gone()
        character?.let {
            if (it.image.isNotEmpty()) {
                Glide.with(fragmentCharacterDetailBinding.root)
                    .load(it.image)
                    .into(fragmentCharacterDetailBinding.characterImageView)
            }

            val genderImage = if (it.gender == "Female") {
                R.drawable.ic_woman
            } else {
                R.drawable.ic_man
            }

            fragmentCharacterDetailBinding.characterGenderImageView.setImageResource(
                genderImage
            )

            fragmentCharacterDetailBinding.characterNameTextView.text =
                it.name
            fragmentCharacterDetailBinding.characterAliveTextView.text =
                it.status
            fragmentCharacterDetailBinding.characterSpeciesTextView.text =
                it.species
            fragmentCharacterDetailBinding.characterOriginTextView.text =
                it.origin
            fragmentCharacterDetailBinding.characterLocationTextView.text =
                it.location
        }
    }

    private fun handleFailure(failure: Failure?) {
        fragmentCharacterDetailBinding.progressBar.gone()
        fragmentCharacterDetailBinding.root.showSnackBar(getString(R.string.something_wrong))
        goBack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCharacterDetailBinding = null
    }
}