package spiridonov.whac_a_mole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import spiridonov.whac_a_mole.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val msp = getSharedPreferences("AppMemory", Context.MODE_PRIVATE)
        val adapterArray: ArrayList<HashMap<String, String>> = ArrayList()

        binding.btnMenu.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    MainActivity().javaClass
                )
            )
        }

        if (msp.contains(KEY_RECORD)) {
            val record = msp.getInt(MainActivity.KEY_RECORD, 0)
            binding.txtHisScore.text = "You record is $record points"
        }

        if (msp.contains(KEY_HISTORY) && msp.getString(KEY_HISTORY, "") != "") {
            val historyArray = msp.getString(KEY_HISTORY, " , ")?.split(",") as ArrayList<String>
            historyArray.forEach {
                if (it.isNotEmpty()) {
                    val myMap = HashMap<String, String>()
                    val myObj = it.split(" - ") as ArrayList<String>
                    myMap["date"] = myObj[1]
                    myMap["point"] = myObj[0]
                    adapterArray.add(myMap)
                }
            }
            val adapter: ListAdapter = SimpleAdapter(
                this@HistoryActivity,
                adapterArray,
                R.layout.history_adapter,
                arrayOf("date", "point"),
                intArrayOf(R.id.txtDate, R.id.txtPoint)
            )
            binding.pointsList.adapter = adapter
        }
    }

    companion object {
        const val KEY_RECORD = "record"
        const val KEY_HISTORY = "history"
    }
}