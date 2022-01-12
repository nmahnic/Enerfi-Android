package com.nicomahnic.enerfiv2.ui.splashFragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.ui.activities.MainActivity
import com.nicomahnic.enerfiv2.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val username = prefs.getString("username","")
        val password = prefs.getString("password","")

        binding.btnLogin.setOnClickListener {
            val edtUsername = binding.edtUsername.text.toString()
            val edtPassword = binding.edtPassword.text.toString()

            if(username == edtUsername &&
                password == edtPassword &&
                username.isNotBlank() &&
                password.isNotBlank()
            ){
                after(TIME_OUT) {
                    startActivity(Intent(context, MainActivity::class.java))
                    activity?.finish()
                }
            } else {
                Snackbar.make(view, "User/Password is incorrect, please try again", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            view.findNavController().navigate(action)
        }

    }

    companion object {
        private const val TIME_OUT:Long = 500 // 2 sec
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }

}