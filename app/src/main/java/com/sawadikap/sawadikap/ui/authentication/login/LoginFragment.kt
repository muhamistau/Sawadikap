package com.sawadikap.sawadikap.ui.authentication.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.domain.model.request.LoginRequest
import com.sawadikap.sawadikap.domain.model.response.LoginResponse
import com.sawadikap.sawadikap.ui.main.MainActivity
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.loginButton -> {
//                checkLogin()
                nextPage()
            }

            R.id.signupButton -> {
                findNavController().navigate(R.id.signUpFragment)
            }
        }
    }

    private fun checkLogin() {
        if (emailEditText.text.isNullOrEmpty() || passwordEditText.text.isNullOrEmpty()) {
            Toast.makeText(activity, "Isi", Toast.LENGTH_SHORT).show()
        } else {
            val sawadikapService = SawadikapRemote.create()
            val loginRequest = LoginRequest(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )

            sawadikapService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(activity, "Login Gagal", Toast.LENGTH_SHORT).show()
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("LOGIN", response.body().toString())
                    if (response.body().toString() == "null") {
                        Toast.makeText(activity, "Email atau Kata sandi salah", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val prefs = activity?.getSharedPreferences(
                            Constant.PREF_NAME,
                            Constant.PRIVATE_MODE
                        )
                        val prefsEditor = prefs?.edit()
                        prefsEditor?.putString(Constant.PREF_USERNAME, response.body()?.username)
                        prefsEditor?.putString(Constant.PREF_EMAIL, response.body()?.email)
                        response.body()?.id?.let { prefsEditor?.putInt(Constant.PREF_ID, it) }
                        prefsEditor?.apply()
                        startActivity(Intent(activity, MainActivity::class.java))
                        activity?.finish()
                    }
                }
            })
        }
    }

    private fun nextPage() {
        // For development purpose, comment these for testing
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }
}
