package com.example.material_electoral

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.material_electoral.login.domain.model.UsuarioModel
import com.example.material_electoral.login.ui.view.LoginActivity
import com.example.material_electoral.login.ui.view.dataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "dataStore")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return when(navController.currentDestination?.id) {
            R.id.casillasFragment -> {
                navController.navigate(R.id.caelsFragment)
                true
            }
            R.id.caelsFragment -> {
                navController.navigate(R.id.mainFragment)
                true
            }
            else -> navController.navigateUp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.signout -> {
                Toast.makeText(this, "Signout", Toast.LENGTH_LONG).show()
                lifecycleScope.launch(Dispatchers.IO) {
                    signOut()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    private suspend fun signOut() {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(("id_usuario"))] = ""
            preferences[stringPreferencesKey(("email_usuario"))] = ""
            preferences[stringPreferencesKey(("password_usuario"))] = ""
            preferences[stringPreferencesKey("nombre_usuario")] = ""
            preferences[stringPreferencesKey(("rol_usuario"))] = ""
            preferences[stringPreferencesKey(("token_usuario"))] = ""
        }
    }
}