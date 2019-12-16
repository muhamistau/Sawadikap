package com.sawadikap.sawadikap.ui.authentication.signup


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.domain.model.request.SignUpRequest
import com.sawadikap.sawadikap.domain.model.response.SignUpResponse
import com.sawadikap.sawadikap.ui.main.MainActivity
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpButton.setOnClickListener {
            if (emailEditText.text.isNullOrEmpty()
                || fullNameEditText.text.isNullOrEmpty()
                || phoneEditText.text.isNullOrEmpty()
                || passwordEditText.text.isNullOrEmpty()
                || passwordConfirmationEditText.text.isNullOrEmpty()
            ) {
                Toast.makeText(activity, "Mohon isi seluruh form", Toast.LENGTH_SHORT).show()
            } else {
                if (passwordEditText.text.toString() == passwordConfirmationEditText.text.toString()) {
                    checkSignUp()
                } else {
                    Toast.makeText(activity, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkSignUp() {
        val sawadikapService = SawadikapRemote.create()
        val signUpRequest = SignUpRequest(
            emailEditText.text.toString(),
            fullNameEditText.text.toString(),
            phoneEditText.text.toString(),
            passwordEditText.text.toString(),
            emailEditText.text.toString()
        )

        sawadikapService.signup(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Toast.makeText(activity, "Daftar Gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if (response.body().toString() == "null") {
                    Toast.makeText(activity, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
                } else {
                    val prefs = activity?.getSharedPreferences(
                        Constant.PREF_NAME,
                        Constant.PRIVATE_MODE
                    )
                    val prefsEditor = prefs?.edit()
                    prefsEditor?.putString(Constant.PREF_USERNAME, response.body()?.email)
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
