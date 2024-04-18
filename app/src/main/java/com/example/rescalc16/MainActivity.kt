package com.example.rescalc16

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rescalc16.ui.theme.ResCalc16Theme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(View.OnClickListener { calc() })
    }

     fun calc(){
         val spinner1 = findViewById<Spinner>(R.id.spinner1)
         val spinner2 = findViewById<Spinner>(R.id.spinner2)
         val spinner3 = findViewById<Spinner>(R.id.spinner3)
         val spinner4 = findViewById<Spinner>(R.id.spinner4)
         val spinner5 = findViewById<Spinner>(R.id.spinner5)
         val spinner6 = findViewById<Spinner>(R.id.spinner6)
         var widerstand = 0.0
         var t = ""
         if (spinner1.selectedItemId.toInt() in intArrayOf(0,1,11,12)) {
             val dialogue = AlertDialog.Builder(this)
             dialogue.setMessage("Fehlerhafte eingabe bei Ring 1!")
             dialogue.show()
             return
         }
         if (spinner2.selectedItemId.toInt() in intArrayOf(0,11,12)) {
             val dialogue = AlertDialog.Builder(this)
             dialogue.setMessage("Fehlerhafte eingabe bei Ring 2!")
             dialogue.show()
             return
         }
         if (spinner5.selectedItem != "-") {
             if (spinner3.selectedItemId.toInt() in intArrayOf(0,11,12)) {
                 val dialogue = AlertDialog.Builder(this)
                 dialogue.setMessage("Fehlerhafte eingabe bei Ring 3!")
                 dialogue.show()
                 return
             }
             widerstand =
                 ((spinner1.selectedItemId - 1) * 100 + (spinner2.selectedItemId - 1) * 10 + (spinner3.selectedItemId - 1)) * 10.0.pow(
                     spinner4.selectedItemId.toInt() - 1
                 )
             t = spinner5.selectedItem.toString()
         }
         else {
             if (spinner3.selectedItemId.toInt() in intArrayOf(0,8,9,10)) {
                 val dialogue = AlertDialog.Builder(this)
                 dialogue.setMessage("Fehlerhafte eingabe bei Ring 3!")
                 dialogue.show()
                 return
             }
             widerstand =
                 ((spinner1.selectedItemId - 1) * 10 + (spinner2.selectedItemId - 1)) * 10.0.pow(
                     spinner3.selectedItemId.toInt() - 1
                 )
             t = spinner4.selectedItem.toString()
             if (spinner6.selectedItem.toString() != "-") {
                 val dialogue = AlertDialog.Builder(this)
                 dialogue.setMessage("Fehlerhafte eingabe bei Ring 6!")
                 dialogue.show()
                 return
             }
         }
         var toleranz = 0.0
         when(t) {
             "Braun" -> toleranz = 1.0
             "Rot" -> toleranz = 2.0
             "Grün" -> toleranz = 0.5
             "Blau" -> toleranz = 0.25
             "Violett" -> toleranz = 0.1
             "Grau" -> toleranz = 0.05
             "Gold" -> toleranz = 5.0
             "Silber" -> toleranz = 10.0
             else -> {
                 val dialogue = AlertDialog.Builder(this)
                 dialogue.setMessage("Fehlerhafte eingabe bei Ring 5!")
                 if (spinner5.selectedItem == "-") {
                     dialogue.setMessage("Fehlerhafte eingabe bei Ring 4!")
                 }
                 dialogue.show()
                 return
             }
         }
         var koeff = 0
         when(spinner6.selectedItem) {
             "-" -> koeff = 0
             "Schwartz" -> koeff = 200
             "Braun" -> koeff = 100
             "Rot" -> koeff = 50
             "Orange" -> koeff = 15
             "Gelb" -> koeff = 25
             "Blau" -> koeff = 10
             "Violett" -> koeff = 5
             else -> {
                 print("test")
                 val dialogue = AlertDialog.Builder(this)
                 dialogue.setMessage("Fehlerhafte eingabe bei Ring 6!")
                 dialogue.show()
                 return
             }
         }

         val widerstand_text = "Widerstand: " + widerstand.toInt().toString() + " Ω\n"
         var toleranz_text = ""
         var koeff_text = ""
         if (toleranz != 0.0) {
             toleranz_text = "Toleranz: " + toleranz.toString() + " %\n"
         }
         if (koeff != 0) {
             koeff_text = "Temp. Koeffizient: " + koeff + " ppm/K"
         }

         val dialogue = AlertDialog.Builder(this)
         dialogue.setMessage(widerstand_text + toleranz_text + koeff_text)
         dialogue.show()
        //return spinner1.selectedItem.toString()

    }


}