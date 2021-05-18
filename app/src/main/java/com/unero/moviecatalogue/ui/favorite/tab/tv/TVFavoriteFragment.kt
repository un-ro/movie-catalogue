package com.unero.moviecatalogue.ui.favorite.tab.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unero.moviecatalogue.databinding.FragmentTVShowBinding
import com.unero.moviecatalogue.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTVShowBinding
    private val viewModel by viewModel<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFav("tv")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }
}