package lat.pam.hellotoast

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private var mCount = 0
    private val model: NameViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView?>(R.id.show_count)
        val buttonCountUp = findViewById<Button?>(R.id.button_count)
        val buttonToast = findViewById<Button?>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button?>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button?>(R.id.button_browser)

        buttonCountUp.setOnClickListener(View.OnClickListener {
            mCount = mCount + 1
            if (mShowCount != null)
            //mShowCount.text = mCount.toString()
                model.currentName.setValue(mCount)
        })


        buttonToast?.setOnClickListener {
            val tulisan: String = mShowCount?.text?.toString() ?: ""
            val toast: Toast = Toast.makeText(this, "Angka yang dimunculkan $tulisan", Toast.LENGTH_LONG)
            toast.show()
        }

        buttonSwitchPage?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        buttonBrowser?.setOnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.data = Uri.parse("https://www.google.com/")
            startActivity(intentbrowse)
        }

        val nameObserver = Observer<Int> { newName ->
            // Update the UI, in this case, a TextView.
            mShowCount.text = newName.toString()
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, nameObserver)


        findViewById<android.view.View?>(R.id.main)?.let { mainView ->
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                insets
            }
        }
    }
}
