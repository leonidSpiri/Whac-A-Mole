package spiridonov.whac_a_mole

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import spiridonov.whac_a_mole.databinding.ActivityGameBinding
import kotlin.random.Random


class GameActivity : AppCompatActivity() {
    private lateinit var btnArray: Array<ImageButton>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnArray = arrayOf(
            binding.imageButton,
            binding.imageButton2,
            binding.imageButton3,
            binding.imageButton4,
            binding.imageButton5,
            binding.imageButton6,
            binding.imageButton7,
            binding.imageButton8,
            binding.imageButton9,
            binding.imageButton10,
            binding.imageButton11,
            binding.imageButton12
        )
        var activeMole = 0
        var score = 0

        for (button in btnArray) {
            button.setOnClickListener {
                if (btnArray[activeMole] == it && btnArray[activeMole].isEnabled) {
                    score += 1
                    binding.txtScore.text = "Score: $score"
                    btnArray[activeMole].isEnabled = false
                    btnArray[activeMole].setImageResource(R.drawable.tile036)
                }
            }
        }

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                btnArray[activeMole].isEnabled = false
                btnArray[activeMole].setImageResource(R.drawable.tile000)
                binding.txtTime.text = "Time: ${millisUntilFinished / 1000}"
                var newMole = Random.nextInt(10)
                while (newMole == activeMole) newMole = Random.nextInt(10)
                activeMole = newMole
                showNewMole(mole = activeMole)
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, ResultActivity().javaClass)
                intent.putExtra("score", score.toString())
                startActivity(intent)
            }
        }.start()


    }

    private fun showNewMole(mole: Int) {
        val btn = btnArray[mole]
        btn.isEnabled = true
        val layers = arrayOfNulls<Drawable>(3)
        layers[0] = ResourcesCompat.getDrawable(resources, R.drawable.tile001, null)
        layers[1] = ResourcesCompat.getDrawable(resources, R.drawable.tile002, null)
        layers[2] = ResourcesCompat.getDrawable(resources, R.drawable.tile003, null)
        val transition = TransitionDrawable(layers)
        transition.isCrossFadeEnabled = true
        btn.setImageDrawable(transition)
        transition.startTransition(70)
    }

}