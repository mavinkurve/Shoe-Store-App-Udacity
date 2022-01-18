package com.udacity.shoestore.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginViewModel
import timber.log.Timber
import android.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        setHasOptionsMenu(true);

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel

        binding.btnSignin.setOnClickListener {
            onLogin(it)
        }

        binding.btnSignup.setOnClickListener {
            onLogin(it)
        }

        viewModel.loginState.observe(viewLifecycleOwner, Observer { loggedIn ->
            if (loggedIn) {
                Timber.d("User is logged in. Navigating to welcome screen")
                findNavController().navigate(LoginFragmentDirections.actionLogin())
            }
        })

        return binding.root
    }

    private fun onLogin(view: View?) {
        // Hide the keyboard.
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        findNavController().navigate(LoginFragmentDirections.actionLogin())
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.shoe_store_title)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Timber.d("Trying to hide the logout button on login screen")
        menu.clear();
    }

}