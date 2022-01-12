package com.nicomahnic.enerfiv2.ui.splashFragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentLoginBinding
import com.nicomahnic.enerfiv2.databinding.FragmentRegisterBinding
import com.nicomahnic.enerfiv2.ui.activities.MainActivity


class RegisterFragment : Fragment(R.layout.fragment_register) {

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
                    putString("username", edtUsername)
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
}