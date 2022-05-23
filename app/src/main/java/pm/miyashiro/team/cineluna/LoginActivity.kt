package pm.miyashiro.team.cineluna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import pm.miyashiro.team.cineluna.databinding.ActivityLoginBinding                                                          //VIEW BINDING

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding                                                                      //VIEW BINDING



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ocultar tool bar antes de settear el contexto
        supportActionBar?.hide()

        binding = ActivityLoginBinding.inflate(layoutInflater)                                                              //VIEW BINDING
        setContentView(binding.root)                                                                                        //VIEW BINDING

        // LISTENERS
        binding.btnLogin.setOnClickListener{
            btnLoginPresionado()
        }
    }

    private fun btnLoginPresionado() {
        if(binding.etNombre.text.toString() == ""){                                         // Si el editText esta vacio    // VB
            binding.etNombre.hint = "Debe ingresar un nombre!"                                                              // VB
            binding.tvNombre.text = "Es obligatorio ingresar su nombre:"                                                    // VB
            binding.tvNombre.setTextColor(ContextCompat.getColor(this, R.color.red))                               // VB
        }else{
            Intent(this, MainActivity::class.java).apply {
                this.putExtra("Nombre", binding.etNombre.text.toString())
                startActivity(this)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Se guarda el estado del edit text
        outState.putString("nombreTemporal", binding.etNombre.text.toString())                                              // VB

        // Se guarda si el usuario ha intentado ingresar sin poner un nombre
        if(binding.tvNombre.currentTextColor == ContextCompat.getColor(this, R.color.red)){                        // VB
            outState.putInt("rojo", 1)
        }else{
            outState.putInt("rojo", 0)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Se restablece el estado del edit text
        binding.etNombre.setText(savedInstanceState.getString("nombreTemporal", "nombre por defecto"))  // VB

        // Se restablece si el usuario ha intentado ingresar sin poner un nombre
        if(savedInstanceState.getInt("rojo")==1){
            binding.etNombre.hint = "Debe ingresar un nombre!"                                                              // VB
            binding.tvNombre.text = "Es obligatorio ingresar su nombre:"                                                    // VB
            binding.tvNombre.setTextColor(ContextCompat.getColor(this, R.color.red))                               // VB
        }
    }
}