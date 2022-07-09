package com.mango.android.presentation.features.characterslist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mango.android.core.core.Failure
import com.mango.android.presentation.R
import com.mango.android.presentation.databinding.FragmentCharactersListBinding
import com.mango.android.presentation.extensions.*
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel
import com.mango.android.presentation.features.characterslist.view.adapter.CharactersListAdapter
import com.mango.android.presentation.features.characterslist.viewmodel.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    // VIEW MODEL
    private val charactersListViewModel : CharactersListViewModel by viewModels()

    //VIEW BINDING
    private var _fragmentCharactersListBinding: FragmentCharactersListBinding? = null
    private val fragmentCharactersListBinding get() = _fragmentCharactersListBinding!!

    //VIEWS
    private lateinit var charactersListAdapter: CharactersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(charactersListViewModel) {
            observe(charactersListLiveData, ::renderCharactersList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharactersListBinding.bind(view)
        _fragmentCharactersListBinding = binding

        initializeView()
        loadCharacters()
    }

    private fun initializeView() {
        setUpCharactersRecyclerView()
        setUpRefreshingCharacters()
    }

    private fun setUpCharactersRecyclerView() {
        charactersListAdapter = CharactersListAdapter(
            characterClickListener = ::onCharacterClicked,
            closeLastItemListener = ::closeToEndOfList
        )

        context?.let {
            fragmentCharactersListBinding.charactersRecyclerview.apply {
                adapter = charactersListAdapter
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun setUpRefreshingCharacters() {
        fragmentCharactersListBinding.swipeRefreshLayout.setOnRefreshListener {
            charactersListViewModel.userRequireRefreshCharactersList()
        }
    }

    private fun loadCharacters() {
        fragmentCharactersListBinding.progressBar.visible()
        charactersListViewModel.currentPageLiveData.value?.let {
            charactersListViewModel.getCharacters(it)
        } ?: run {
            charactersListViewModel.userRequireGetNewCharacters()
        }
    }

    private fun renderCharactersList(collection: List<CharacterUIModel>?) {
        fragmentCharactersListBinding.progressBar.gone()
        fragmentCharactersListBinding.swipeRefreshLayout.isRefreshing = false
        if(collection.isNullOrEmpty()) {
            handleFailure(null)
        } else {
            charactersListAdapter.submitList(collection)
        }
    }

    private fun handleFailure(failure: Failure?) {
        fragmentCharactersListBinding.swipeRefreshLayout.isRefreshing = false
        fragmentCharactersListBinding.progressBar.gone()

        failure?.let {
            val message = when(it) {
                Failure.EmptyResponse -> getString(R.string.empty_list)
                else -> getString(R.string.something_wrong)
            }

            fragmentCharactersListBinding.root.showSnackBar(message)
        } ?: run {
            fragmentCharactersListBinding.root.showSnackBar(getString(R.string.something_wrong))
        }
    }

    private fun onCharacterClicked(characterUIModel: CharacterUIModel) {
        //TODO do when navigation
    }

    private fun closeToEndOfList() {
        charactersListViewModel.userRequireGetNewCharacters()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCharactersListBinding = null
    }
}