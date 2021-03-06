package com.unero.moviecatalogue.ui.home.tabs.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.unero.moviecatalogue.databinding.FragmentTVShowBinding
import com.unero.moviecatalogue.ui.home.PageViewModel

class TVShowFragment : Fragment() {

    private lateinit var binding: FragmentTVShowBinding
    private lateinit var viewModel: PageViewModel
    private lateinit var tvAdapter: TVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRV()

        viewModel.tv.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                val shows = it.body()?.results
                if (shows != null) {
                    tvAdapter.apply {
                        setShows(shows)
                        notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun setupRV() {
        with(binding.rvTv) {
            tvAdapter = TVAdapter()
            adapter = tvAdapter
            setHasFixedSize(true)
        }
    }
}