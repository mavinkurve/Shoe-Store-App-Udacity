package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeDetailViewBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, { it ->
            Timber.d("Shoe count is ${it.size}")
            for (shoe in it) {
                val shoeListLayout = DataBindingUtil.inflate<ShoeDetailViewBinding>(
                    inflater,
                    R.layout.shoe_detail_view,
                    container,
                    false
                )
                shoeListLayout.shoe = shoe
                binding.linearLayoutShoeList.addView(shoeListLayout.root)
            }
        })

        binding.fab.setOnClickListener {
            Timber.d("Navigating to add shoe detail fragment")
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.shoe_list_title)
    }
}