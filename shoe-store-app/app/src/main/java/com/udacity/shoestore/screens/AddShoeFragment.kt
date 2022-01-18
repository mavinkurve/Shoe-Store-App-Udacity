package com.udacity.shoestore.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [AddShoeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddShoeFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    private lateinit var binding: FragmentAddShoeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_shoe,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.shoesViewModel = viewModel

        viewModel.save.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigateUp()
                viewModel.onSaveComplete()
            }
        })

        viewModel.cancel.observe(viewLifecycleOwner, {
          if(it) {
              findNavController().navigateUp()
              viewModel.onCancelComplete()
          }
        })

        viewModel.invalidFormData.observe(viewLifecycleOwner) {
            if (it) {
                Timber.d("Showing form data toast")
                Toast.makeText(requireContext(), "All shoe details required", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonSave.setOnClickListener{
            // hide the keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
            viewModel.onClickSave()
        }

        binding.buttonCancel.setOnClickListener{
            viewModel.onClickCancel()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.add_shoe_title)
    }
}