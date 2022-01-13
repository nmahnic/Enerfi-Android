package com.nicomahnic.enerfiv2.ui.splashFragments.register

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
import com.nicomahnic.enerfiv2.databinding.FragmentRegisterBinding
import com.nicomahnic.enerfiv2.ui.activities.MainActivity
import com.nicomahnic.enerfiv2.ui.splashFragments.login.*
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterDataState, RegisterAction, RegisterEvent, RegisterVM>
    (R.layout.fragment_register) 
{

    override val viewModel: RegisterVM by viewModels()
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        v = view

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.btnRegister.setOnClickListener {
            viewModel.process(RegisterEvent.Validate(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtVerifyPassword.text.toString(),
                prefs
            ))
        }
    }

    override fun renderViewState(viewState: RegisterDataState) {
        when (viewState.state) {
            is RegisterState.Validated -> {
                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                with(prefs.edit()){
                    putString("userMail",binding.edtUsername.text.toString())
                    putString("password",binding.edtPassword.text.toString())
                    apply()
                }
                viewModel.process(RegisterEvent.Register(viewState.mail!!, viewState.passwd!!))
            }
            is RegisterState.NotValidated -> {
                Snackbar.make(v, "Something was wrong, please try again", Snackbar.LENGTH_LONG).show()
            }
            is RegisterState.GoToHome -> {
                after(TIME_OUT) {
                    startActivity(Intent(context, MainActivity::class.java))
//                    activity?.finish()
                }
            }
            is RegisterState.Registered -> {
                Snackbar.make(v, "Registered", Snackbar.LENGTH_LONG).show()
            }
            else -> {
                Log.d("NM", "${viewState.state}")
            }
        }
    }

    override fun renderViewEffect(viewEffect: RegisterAction) {
        when (viewEffect) {
            is RegisterAction.OK -> {
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