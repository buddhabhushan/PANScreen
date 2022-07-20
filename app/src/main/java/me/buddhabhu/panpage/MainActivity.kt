package me.buddhabhu.panpage

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.buddhabhu.panpage.ui.PANScreenUI
import me.buddhabhu.panpage.ui.theme.PANPageTheme
import me.buddhabhu.panpage.utils.isValidBirthDate
import me.buddhabhu.panpage.utils.isValidPAN
import me.buddhabhu.panpage.viewmodel.PanViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(PanViewModel::class.java) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PANPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PANScreenUI(
                        viewModel = viewModel,
                        onButtonClick = ::onNextClick,
                        onRejectClick = ::onRejectClicked,
                    )
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onNextClick(pan: String, date: String, month: String, year: String) {
        viewModel.isPanValid.value = isValidPAN(pan)
        if(year.length < 4 || date.isEmpty() || month.isEmpty()) {
            viewModel.isBirthDateValid.value = false
        } else {
            viewModel.isBirthDateValid.value = isValidBirthDate(date, month, year)
        }

        if(viewModel.isPanValid.value && viewModel.isBirthDateValid.value) {
            Toast.makeText(applicationContext, getString(R.string.label_success), Toast.LENGTH_LONG).show()
            lifecycleScope.launch {
                delay(3000)
                finish()
            }
        }

    }

    fun onRejectClicked() {
        finish()
    }
}


