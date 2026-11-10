package lat.pam.hellotoast

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val buttonContact = findViewById<Button?>(R.id.button_contact)
        buttonContact?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:081978967517")
            }
            startActivity(intent)
        }

        val buttonYoutube = findViewById<Button?>(R.id.button_youtube)
        buttonYoutube?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"))
            intent.setPackage("com.google.android.youtube")
            try {
                startActivity(intent)
            } catch (_: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")))
            }
        }

        val buttonMole = findViewById<Button?>(R.id.button_mole)
        buttonMole?.setOnClickListener {
            val molePackageName = "com.mobile.legends"
            val launchIntent = packageManager.getLaunchIntentForPackage(molePackageName)

            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                // Coba buka aplikasi secara langsung jika getLaunchIntentForPackage bernilai null
                try {
                    val directIntent = Intent(Intent.ACTION_MAIN).apply {
                        addCategory(Intent.CATEGORY_LAUNCHER)
                        setPackage(molePackageName)
                    }
                    startActivity(directIntent)
                } catch (_: ActivityNotFoundException) {
                    // Jika memang tidak terinstall di HP, buka di Play Store
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$molePackageName")))
                }
            }
        }

        findViewById<android.view.View?>(R.id.main)?.let { mainView ->
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}
