package spiridonov.whac_a_mole

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spiridonov.whac_a_mole.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenu.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    MainActivity().javaClass
                )
            )
        }

        binding.txtVK.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/leonid.spiri"))
            startActivity(browserIntent)
        }

        binding.txtEmail.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "leonid.spiri@yandex.ru", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About Whac A Mole App")
            startActivity(Intent.createChooser(emailIntent, "Send message"))
        }
        binding.txtGitHub.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/leonidSpiri"))
            startActivity(browserIntent)
        }

    }
}