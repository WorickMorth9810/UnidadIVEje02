package com.example.unidadiveje02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    private val archivo ="datos.txt"
    private lateinit var edContenido : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edContenido=findViewById(R.id.edContenido)
    }

    fun lectura(v:View){
        Log.e("ARCHIVOAPP","Se Presiono Lectura")
        val fis=openFileInput(archivo)
        val isr=InputStreamReader(fis)
        val bur=BufferedReader(isr)
        var linea:String?= ""
        var Contenido= StringBuilder()
        while (linea!=null){
            linea=bur.readLine()
            if(linea!=null) {
                Log.e("ARCHIVOAPP", linea)
                Contenido.append(linea)
                Contenido.append("\n")
            }
        }
        edContenido.setText(Contenido)
        bur.close()
        isr.close()
        fis.close()
    }
    fun escritura(v:View){
        Log.e("ARCHIVOAPP","Se Presiono Escritura")
        val fos=openFileOutput(archivo, Context.MODE_PRIVATE)
        val contenidoTexto=edContenido.text.toString()

        fos.write(contenidoTexto.toByteArray())
        fos.close()
        edContenido.text.clear()
        Snackbar.make(v, "Se Grabo", Snackbar.LENGTH_LONG).show()


    }
}