package com.nicomahnic.enerfiv2.ui.splashFragments.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.ui.activities.MainActivity
import com.nicomahnic.enerfiv2.databinding.FragmentLoginBinding
import com.nicomahnic.enerfiv2.ui.splashFragments.register.RegisterEvent
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginDataState, LoginAction, LoginEvent, LoginVM>
    (R.layout.fragment_login)
{

    override val viewModel: LoginVM by viewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        v = view

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.btnLogin.setOnClickListener {

            viewModel.process(LoginEvent.Validate(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString(),
                prefs.getString("userMail","")!!,
                prefs.getString("password","")!!
            ))
        }

        binding.btnRegister.setOnClickListener {
            viewModel.process(LoginEvent.Register)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
//        activity?.finish()
    }

    override fun renderViewState(viewState: LoginDataState) {
        when (viewState.state) {
            is LoginState.NotValidated -> {
                Snackbar.make(v, "User/Password is incorrect, please try again", Snackbar.LENGTH_LONG).show()
            }
            is LoginState.Validated -> {
                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                with(prefs.edit()){
                    putString("userMail",binding.edtUsername.text.toString())
                    putString("password",binding.edtPassword.text.toString())
                    apply()
                }
                viewModel.process(LoginEvent.GoToHome)
            }
            is LoginState.GoToRegister -> {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                v.findNavController().navigate(action)
            }
            is LoginState.GoToHome -> {
                after(TIME_OUT) {
                    startActivity(Intent(context, MainActivity::class.java))
//                    activity?.finish()
                }
            }
            else -> {
                Log.d("NM", "${viewState.state}")
            }
        }
    }

    override fun renderViewEffect(viewEffect: LoginAction) {
        when (viewEffect) {
            is LoginAction.OK -> {
                Log.d("NM", "$viewEffect")
            }
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