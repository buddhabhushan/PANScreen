package me.buddhabhu.panpage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.buddhabhu.panpage.R
import me.buddhabhu.panpage.viewmodel.PanViewModel

@Composable
fun PANScreenUI(
    viewModel: PanViewModel,
    onButtonClick: (String, String, String, String) -> Unit,
    onRejectClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
    ) {
        val pan = remember { mutableStateOf("") }
        val date = remember { mutableStateOf("") }
        val month = remember { mutableStateOf("") }
        val year = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.size(50.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "Icon",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = stringResource(R.string.header),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.pan_number),
            color = Color.Gray,
            fontSize = 10.sp,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pan.value,
            onValueChange = {
                if(it.length <= 10) {
                    pan.value = it
                }
            },
            label = { Text(text = stringResource(R.string.pan_number)) },
            placeholder = { Text(text = stringResource(R.string.enter_pan_number))},
            isError = !viewModel.isPanValid.value,
        )
        if(!viewModel.isPanValid.value) {
            Text(
                text = stringResource(R.string.error_pan),
                color = Color.Red,
                fontSize = 10.sp,
            )
        }

        Spacer(modifier = Modifier.size(50.dp))

        Text(
            text = stringResource(R.string.birth_date),
            color = Color.Gray,
            fontSize = 10.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = date.value,
                onValueChange = {
                    if(it.length <= 2) {
                        date.value = it
                    }
                },
                label = { Text(text = stringResource(R.string.date)) },
                placeholder = { Text(text = stringResource(R.string.date))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = !viewModel.isBirthDateValid.value,
            )

            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = month.value,
                onValueChange = {
                    if(it.length <= 2) {
                        month.value = it
                    }
                },
                label = { Text(text = stringResource(R.string.month)) },
                placeholder = { Text(text = stringResource(R.string.month))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = !viewModel.isBirthDateValid.value,
            )

            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = year.value,
                onValueChange = {
                    if(it.length <= 4) {
                        year.value = it
                    }
                },
                label = { Text(text = stringResource(R.string.year)) },
                placeholder = { Text(text = stringResource(R.string.year))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = !viewModel.isBirthDateValid.value,
            )
        }

        if(!viewModel.isBirthDateValid.value) {
            Text(
                text = stringResource(R.string.error_birth_date),
                color = Color.Red,
                fontSize = 10.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.details),
            fontSize = 13.sp,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            content = { Text(text = stringResource(R.string.next), fontSize = 15.sp)},
            onClick = { onButtonClick(pan.value, date.value, month.value, year.value) }
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onRejectClick() },
            text = stringResource(R.string.label_no_pan),
            color = Color.Magenta,
        )

    }

    
}

@Preview
@Composable
fun PANScreenUIPreview() {
    MaterialTheme {
        PANScreenUI(
            viewModel = PanViewModel(),
            onButtonClick = { _,_,_,_ -> Unit},
            onRejectClick = {}
        )
    }
}

