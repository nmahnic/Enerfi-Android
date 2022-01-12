package com.nicomahnic.enerfiv2.ui.splashFragments.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.ui.activities.MainActivity
import com.nicomahnic.enerfiv2.databinding.FragmentLoginBinding
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginDataState, LoginAction, LoginEvent, LoginVM>
    (R.layout.fragment_login)
{

    override val viewModel: LoginVM by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val userMail = prefs.getString("userMail","")
        val password = prefs.getString("password","")

        binding.btnLogin.setOnClickListener {
            val edtUsername = binding.edtUsername.text.toString()
            val edtPassword = binding.edtPassword.text.toString()

            if(userMail == edtUsername &&
                password == edtPassword &&
                userMail.isNotBlank() &&
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

    override fun renderViewState(viewState: LoginDataState) {
//        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: LoginAction) {
//        TODO("Not yet implemented")
    }

}