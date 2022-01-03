package com.udacity.shoestore.screen.login

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import android.content.Context.INPUT_METHOD_SERVICE as INPUT_METHOD_SERVICE1


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
                Log.i("LoginFragment", "Navigating to welcome screen")
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())


            }
        })

        return binding.root
    }

    private fun onLogin(view: View?) {
        // Hide the keyboard.
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}