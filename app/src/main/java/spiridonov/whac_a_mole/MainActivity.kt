package spiridonov.whac_a_mole

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val msp = getSharedPreferences("AppMemory", Context.MODE_PRIVATE)
        if (msp.contains(KEY_HISTORY) && msp.getString(KEY_HISTORY, "") != "") {
            val historyArray = msp.getString(KEY_HISTORY, " , ")?.split(",") as ArrayList<String>
            Log.d("KEY_RECORD", historyArray.toString())
            Log.d("historyArray", historyArray.size.toString())
            Log.d("historyArray", historyArray.toString())
        }
    }

    companion object{
        const val KEY_RECORD = "record"
        const val KEY_HISTORY = "history"
    }
}