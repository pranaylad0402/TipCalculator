package com.pranay.tipcalculator

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private var radioGroup: RadioGroup? = null
    private lateinit var radioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tipAmount: TextView = findViewById(R.id.tip_amount)
        val tipInput: EditText = findViewById(R.id.tip_input)
        val calculate: Button = findViewById(R.id.calculate)
        val roundUpTip: SwitchMaterial =findViewById(R.id.round_up_tip)
        radioGroup = findViewById(R.id.radio_group)
        calculate.setOnClickListener {
            val enteredTip: String = tipInput.text.toString()
            if (enteredTip.trim().isNotEmpty()) {
                // calculation logic
                val selectedOption: Int = radioGroup!!.checkedRadioButtonId
                val roundTheTip:Boolean=roundUpTip.isChecked
                val tipAmountStr="Tip Amount: Rs."
                radioButton = findViewById(selectedOption)
                when (radioButton.id) {
                    R.id.option_twenty_percent ->
                        tipAmount.text =tipAmountStr+ calculateTip(enteredTip.toDouble(), 20.0,roundTheTip).toString()

                    R.id.option_eighteen_percent ->
                        tipAmount.text =tipAmountStr+ calculateTip(enteredTip.toDouble(), 18.0,roundTheTip).toString()

                    R.id.option_fifteen_percent ->
                        tipAmount.text =tipAmountStr+ calculateTip(enteredTip.toDouble(), 15.0,roundTheTip).toString()
                }
                closeKeyBoard()
            } else {
                showToast("Please Enter Tip")
            }
        }
    }

    private fun calculateTip(amount: Double, tipPercentage: Double,ischecked:Boolean): Double {
        if(ischecked){
            return ceil(amount * (tipPercentage / 100))
        }
        return amount * (tipPercentage / 100)
    }

    private fun showToast(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}