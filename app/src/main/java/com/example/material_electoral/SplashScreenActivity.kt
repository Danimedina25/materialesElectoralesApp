package com.example.material_electoral

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.ui.view.LoginActivity
import com.example.material_electoral.login.ui.view.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import kotlin.text.Charsets.UTF_8

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreen.setKeepOnScreenCondition{true}

        lifecycleScope.launch(Dispatchers.IO) {
            getDataUsuario().collect{

                if(it.token === ""){
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }else{
                    val tokenExpired = isExpired(it.token)
                    Log.e("token", tokenExpired.toString())

                    if(tokenExpired){
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                    }else{
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                }
                finish()
            }
        }
    }

    private fun getDataUsuario() = dataStore.data.map {
        UsuarioModel(
            it[stringPreferencesKey("id_usuario")].orEmpty(),
            it[stringPreferencesKey("email_usuario")].orEmpty(),
            it[stringPreferencesKey("password_usuario")].orEmpty(),
            it[stringPreferencesKey("nombre_usuario")].orEmpty(),
            it[stringPreferencesKey("rol_usuario")].orEmpty(),
            it[stringPreferencesKey("token_usuario")].orEmpty()
        )
    }

    @Throws(Exception::class)
    fun isExpired(JWTEncoded: String): Boolean {
        try {
            val split = JWTEncoded.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            //Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
            //Log.d("JWT_DECODED", "Body: " + getJson(split[1]))

            val json = JSONObject(getJson(split[1]))

            return json.get("exp").toString().toLong() < (System.currentTimeMillis() / 1000)
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
        return false
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, UTF_8)
    }


}