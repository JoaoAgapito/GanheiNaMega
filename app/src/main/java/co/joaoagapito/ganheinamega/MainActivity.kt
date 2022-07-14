package co.joaoagapito.ganheinamega

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DECIDE O QUE O APP VAI FAZER (QUAL ATIVIDADE IRA ABRIR)
        setContentView(R.layout.activity_main)

        // BUSCAR OS OBJETOS E TER REFERECIA DELES:
        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)


        //CLICK - OPÇÃO 1: USANDO O XML: (obrigatorio declara um onClick no objeto)
        // fun buttonClicked (view: View){}

        //CLICK - OPÇÃO 2: VARIAVEL QUE SEJA DO TIPO View.OnClickListener (interface)
        //btnGenerate.setOnClickListener(buttonClickListener)
        //val buttonClickListener = object : View.OnClickListener{
        //override fun onClick(p0: View?) {}

        //CLICK - OPÇÃO 3: DECLARAR NO BLOCO DE CODIGO O QUE SERÁ DISPARADO PELO onClickListener
        btnGenerate.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        //validação para saber se o campo foi preenchido
        if (text.isNotEmpty()) {
            val qtd = text.toInt() // metodo para converter string em inteiro
            if (qtd >= 6 && qtd <= 15) {

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

            } else
                Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
        }


    }

}