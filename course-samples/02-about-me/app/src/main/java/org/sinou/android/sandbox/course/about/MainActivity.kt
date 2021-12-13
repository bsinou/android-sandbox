package org.sinou.android.sandbox.course.about

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sinou.android.sandbox.course.about.databinding.ActivityMainBinding

/** Shows basics for data binding */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myNameData: MyName = MyName("Bruno")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myNameData

        // findViewById<Button>(R.id.done_button).setOnClickListener { handleNickname(it) }
        binding.doneButton.setOnClickListener { handleNickname(it) }
    }

    fun handleNickname(v: View) {
//        val userInput = findViewById<EditText>(R.id.nickname_edit)
//        val resultTxt = findViewById<TextView>(R.id.nickname_text)
//        resultTxt.text = userInput.text
//        userInput.visibility = View.GONE
//        v.visibility = View.GONE
//        resultTxt.visibility = View.VISIBLE

        binding.apply {
            myName.nickname = nicknameEdit.text.toString()
            // Important: trigger re-paint
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Handle hiding the keyboard when the user clicks on the button
        // Still to be explained
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}