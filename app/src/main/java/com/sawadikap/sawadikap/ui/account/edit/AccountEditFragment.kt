package com.sawadikap.sawadikap.ui.account.edit


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.entity.User
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_account_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountEditFragment : Fragment(), View.OnClickListener {

    var userId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs =
            activity?.getSharedPreferences(Constant.PREF_NAME, Constant.PRIVATE_MODE)

        userId = prefs?.getInt(Constant.PREF_ID, 0)
        getUserCred(userId)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveButton -> {
                updateUserCred(userId)
            }
        }
    }

    private fun updateUserCred(userId: Int?) {
        val sawadikapService = SawadikapRemote.create()
        val newPassword: String =
            if (passwordEditText.text.toString().isNotEmpty()) passwordEditText.text.toString()
            else ""

        if (userId != null) {
            val user = User(
                id = userId,
                name = nameEditText.text.toString(),
                email = emailEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                password = newPassword
            )

            sawadikapService.updateUserCreds(user).enqueue(object : Callback<Int> {
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    val data = response.body()
                    if (data != null) {
//                        activity?.runOnUiThread {
//                            Toast.makeText(activity, "TEST", Toast.LENGTH_SHORT).show()
//                        }
                        if (response.isSuccessful) {
                            val prefs =
                                activity?.getSharedPreferences(
                                    Constant.PREF_NAME,
                                    Constant.PRIVATE_MODE
                                )
                            val prefEditor = prefs?.edit()
                            prefEditor
                                ?.putString(Constant.PREF_USERNAME, nameEditText.text.toString())
                            prefEditor
                                ?.putString(Constant.PREF_EMAIL, emailEditText.text.toString())
                            prefEditor?.apply()
                            findNavController().navigateUp()
                        }
                    }
                }
            })
        }
    }

    private fun getUserCred(userId: Int?) {
        val sawadikapService = SawadikapRemote.create()
        if (userId != null) {
            sawadikapService.getUserCred(userId).enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val data = response.body()
                    if (data != null) {
//                        activity?.runOnUiThread {
//                            Toast.makeText(activity, "TEST", Toast.LENGTH_SHORT).show()
//                        }
                        nameEditText.setText(data[0].name)
                        emailEditText.setText(data[0].email)
                        phoneEditText.setText(data[0].phone)
                    }
                }
            })
        }
    }
}
