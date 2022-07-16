package co.joaoagapito.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefts: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DECIDE O QUE O APP VAI FAZER (QUAL ATIVIDADE IRA ABRIR)
        setContentView(R.layout.activity_main)


        // BUSCAR OS OBJETOS E TER REFERECIA DELES:
        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        // banco de dados de preferencia
        prefts = getSharedPreferences("db", Context.MODE_PRIVATE)

        val result = prefts.getString("result", null)
        if (result != null){
            txtResult.text = "Ultima aposta: $result"
        }

        btnGenerate.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        //validação para saber se o campo foi preenchido
        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }
        val qtd = text.toInt() // metodo para converter string em inteiro

        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }
        val numbers = mutableSetOf<Int>()
        val random = Random

        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)

            if (numbers.size == qtd) {
                break
            }
        }
        txtResult.text = numbers.joinToString(" - ")

        val editor = prefts.edit()
        editor.putString("result", txtResult.text.toString())
        val saved = editor.commit()
        Log.i("teste","foi gravado $saved")


    }

}