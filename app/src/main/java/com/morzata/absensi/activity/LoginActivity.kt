package com.morzata.absensi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.morzata.absensi.R
import com.morzata.absensi.network.ApiURL
import com.morzata.absensi.network.VolleySingleton
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        backBtn.setOnClickListener{
            finish()
        }
        btnLogin.setOnClickListener {
            userLogin()
        }
    }

    fun userLogin() {
        val email = tvEmail.text.toString()
        val password = tvPassword.text.toString()

        if (email.isEmpty()){
            tvEmail.error = "Email tidak boleh kososng"
        }
        if (password.isEmpty()){
            tvPassword.error = "Password tidak boleh kosong"
        }

        val stringRequest = object : StringRequest(Request.Method.POST, ApiURL.getLogin(email, password),
            Response.Listener {response ->
                try {
                    val obj = JSONObject(response)
                    val success = obj.getString("success")

                   if (success.equals("1")) {
                       val data = obj.getJSONObject("user")

                       startActivity<AbsenActivity>()
                   } else {
                       toast("Email dan Password tidak sesuai.")
                   }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> toast(error.message.toString()) })  {

            @Throws
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                params["password"] = password
                return params
            }
        }
        VolleySingleton.getInstance(this).addRequestQueue(stringRequest)
    }
}
