package spiridonov.whac_a_mole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spiridonov.whac_a_mole.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val msp = getSharedPreferences("AppMemory", Context.MODE_PRIVATE)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    GameActivity().javaClass
                )
            )
        }
        binding.btnHistory.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    HistoryActivity().javaClass
                )
            )
        }
        binding.btnAbout.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    AboutActivity().javaClass
                )
            )
        }

        if (msp.contains(KEY_RECORD)) {
            val record = msp.getInt(KEY_RECORD, 0)
            binding.txtHisScore.text = "You record is $record points"
        }
    }

    companion object {
        const val KEY_RECORD = "record"
    }
}