package br.com.omnitrix.imctrix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.util.*
import br.com.omnitrix.imctrix.*
import kotlin.math.pow
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtHeight.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(!edtHeight.text.isEmpty()){
                    var i: Double = s.toString().toDouble()
                    if (i >= 0.4 && i <= 2.5) {
                        seekHeight.setProgress((i * 100).toInt()); // This ensures 0-120 value for seekbar
                    }else{
                        Toast.makeText(this@MainActivity, "Valor inválido", Toast.LENGTH_SHORT).show()
                        edtHeight.setText((0.4).toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        seekHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                var value : Double
                value = i.toDouble()/100
                edtHeight.setText("$value")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

        })
        edtWeight.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(!edtHeight.text.isEmpty()){
                    var i: Int = s.toString().toInt()
                    if (i >= 20 && i <= 300) {
                        seekWeight.setProgress((i).toInt()); // This ensures 0-120 value for seekbar
                    }else{
                        Toast.makeText(this@MainActivity, "Valor inválido", Toast.LENGTH_SHORT).show()
                        edtWeight.setText((20).toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        seekWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                edtWeight.setText("$i")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        btnCalculate.setOnClickListener{
            if(edtHeight.text.toString().isNotEmpty() && edtHeight.text.toString().isNotEmpty()){
                var heigth = edtHeight.text.toString().toDouble()
                var weigth = edtWeight.text.toString().toDouble()
                var BMICalc = weigth / heigth.pow(2)
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Calculo de IMC")
                    .setMessage(String.format("Seu IMC é %.1f", BMICalc))
                    .setNeutralButton("Fechar"){dialog, which ->
                        dialog.cancel()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }else{
                Toast.makeText(this@MainActivity,"Insira um valor válido", Toast.LENGTH_SHORT).show()
            }

        }

        btnTable.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.table_image, null)
            //AlertDialogBuilder

            AlertDialog.Builder(this).apply {
                setTitle("Tabela IMC")
                setView(mDialogView)
                setNeutralButton("Fechar"){dialog, which ->
                    dialog.cancel()
                    dialog.dismiss()
                }.create().show()
            }
        }
    }
}
