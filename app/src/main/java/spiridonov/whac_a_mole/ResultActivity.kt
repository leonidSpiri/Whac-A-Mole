package spiridonov.whac_a_mole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spiridonov.whac_a_mole.databinding.ActivityResultBinding
import java.util.*


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAgain.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    GameActivity().javaClass
                )
            )
        }
        binding.btnMain.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    MainActivity().javaClass
                )
            )
        }
        val msp = getSharedPreferences("AppMemory", Context.MODE_PRIVATE)
        val editor = msp.edit()
        val calendar = Calendar.getInstance()
        var month = calendar.get(Calendar.MONTH)
        month++
        val intent = intent
        val score = (intent.getStringExtra("score") ?: "0").toInt()
        binding.txtScoreRes.text = "You scored $score points"

        var record = 0
        if (msp.contains(KEY_RECORD)) record = msp.getInt(KEY_RECORD, 0)
        if (record < score) {
            binding.txtScoreRes.text = "You scored $score points\nIt is your new record!"
            editor.putInt(KEY_RECORD, score)
        }
        var history = ""
        if (msp.contains(KEY_HISTORY)) history = msp.getString(KEY_HISTORY, "") ?: ""
        history += "$score - ${calendar.get(Calendar.DAY_OF_MONTH)}.$month.${calendar.get(Calendar.YEAR)},"
        editor.putString(KEY_HISTORY, history)
        editor.apply()

    }

    companion object {
        const val KEY_RECORD = "record"
        const val KEY_HISTORY = "history"
    }
}