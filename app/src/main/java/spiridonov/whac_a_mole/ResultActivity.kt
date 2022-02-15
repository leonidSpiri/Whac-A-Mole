package spiridonov.whac_a_mole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spiridonov.whac_a_mole.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val score  =  intent.getStringExtra("score") ?: 0
        binding.txtScoreRes.text = "You scored $score points"

    }
}