package com.unero.moviecatalogue.ui.home.tabs.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.unero.moviecatalogue.databinding.FragmentTVShowBinding
import com.unero.moviecatalogue.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment() {

    private lateinit var binding: FragmentTVShowBinding
    private val viewModel by viewModel<SharedViewModel>()
    private lateinit var tvAdapter: TVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.topTV()
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
            if (it.isNotEmpty()) {
                tvAdapter.setShows(it)
                tvAdapter.notifyDataSetChanged()
            } else {
                showMessage("Unknown Error")
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, {
            showMessage(it)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, {
            binding.shimmerFrameLayout.apply {
                if (!it) {
                    stopShimmer()
                    visibility = View.GONE
                }
            }
        })

        binding.btnError.setOnClickListener {
            retry()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    private fun retry() {
        viewModel.topTV()
        binding.apply {
            iconError.visibility = View.GONE
            btnError.visibility = View.GONE
        }
    }

    private fun showMessage(message: String) {
        binding.apply {
            iconError.visibility = View.VISIBLE
            btnError.visibility = View.VISIBLE
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupRV() {
        with(binding.rvTv) {
            tvAdapter = TVAdapter()
            adapter = tvAdapter
            setHasFixedSize(true)
        }
    }
}
