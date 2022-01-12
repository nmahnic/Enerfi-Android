package com.nicomahnic.enerfiv2.ui.splashFragments.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentRegisterBinding
import com.nicomahnic.enerfiv2.ui.activities.MainActivity
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterDataState, RegisterAction, RegisterEvent, RegisterVM>
    (R.layout.fragment_register) 
{

    override val viewModel: RegisterVM by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.btnRegister.setOnClickListener {
            val edtUsername = binding.edtUsername.text.toString()
            val edtPassword = binding.edtPassword.text.toString()
            val edtVerifyPasswd = binding.edtVerifyPassword.text.toString()

            if(edtUsername.isNotBlank() && edtPassword.isNotBlank() && edtPassword == edtVerifyPasswd){
                with (prefs.edit()) {
                    putString("userMail", edtUsername)
                    putString("password", edtPassword)
                    apply()
                }
                after(TIME_OUT) {
                    startActivity(Intent(context, MainActivity::class.java))
                    activity?.finish()
                }
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

    override fun renderViewState(viewState: RegisterDataState) {
//        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: RegisterAction) {
//        TODO("Not yet implemented")
    }
}