package com.messer_amd.electrician.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messer_amd.electrician.R
import java.math.BigDecimal
import java.math.RoundingMode

// Настроить цвет закона Ома
//выбрать цвета кнопки
// выбрать цвета выделения активного поля ввода
// выбрать цвет рамки поля вывода

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Amperage() {
    var currentInput by remember { mutableStateOf("") }
    var resistanceInput by remember { mutableStateOf("") }
    var amperageResult by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Unspecified),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = stringResource(R.string.amperage_title),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
            ) {
                Row {
                    Image(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(64.dp),
                        painter = painterResource(R.drawable.amperage),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = stringResource(R.string.ohm_law),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .weight(0.7f),
                        text = stringResource(R.string.current_text),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    //EDIT VOLTAGE
                    EditNumberField(
                        label = R.string.label_volt,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        value = currentInput,
                        onValueChanged = { currentInput = it },
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .weight(0.7f),
                        text = stringResource(R.string.resistance_text),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    EditNumberField(
                        label = R.string.label_ohm,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        value = resistanceInput,
                        onValueChanged = { resistanceInput = it },
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            // place for button
            Button(
                onClick = {
                    val current = currentInput.toFloatOrNull() // напряжение
                    val resistance = resistanceInput.toFloatOrNull()// сопротивление
                    amperageResult = if (current != null &&  resistance != null) {
                        amperageResult(current, resistance)
                    } else {
                        "☺" // alt + 1
                    }
                },
                elevation = ButtonDefaults.buttonElevation(4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.calculate_button_text),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
        // place for result line ИТОГОВАЯ СТРОКА
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(0.8f),
                    text = stringResource(R.string.amperage_result), // СИЛА ТОКА(I):
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
//                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                    //Spacer(modifier = Modifier.width(10.dp))
                // Box with result
                Box(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(start = 10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Green,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = amperageResult,
                        modifier = Modifier.padding(4.dp),
                        fontSize = 18.sp
                    )
                }
               // Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(0.7f),
                    text = stringResource(R.string.label_ampere),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

// calculate fun

fun amperageResult(current: Float, resistance: Float): String {
    if (current == 0f || resistance == 0f) {
        return "-273.15°C" // alt + 0176
    }
    val amperage = current / resistance
    val formattedAmperage = BigDecimal(amperage.toDouble()).setScale(5, RoundingMode.HALF_UP)
    return formattedAmperage.stripTrailingZeros().toPlainString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxDigits: Int = 8 // Максимальное количество цифр
) {
    val maxLength = maxDigits + 1 // Учитываем точку или запятую

    val validatedValue = if (value.count { it == '.' || it == ',' } > 1) {
        // Обработка ситуации, когда введено более одной точки или запятой
        // Например, можно игнорировать введенное значение или отображать сообщение об ошибке
        ""
    } else if (value.length > maxLength) {
        value.substring(0, maxLength)
    }else{
        value.replace(',', '.') // Заменяем запятую на точку
    }

    OutlinedTextField(
        value = validatedValue,
        onValueChange = onValueChanged,
        modifier = modifier,

        label = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(label))
            }
        },
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            textAlign = TextAlign.Center, // располагаем введенные значения по центру
            fontSize = 18.sp // размер вводимых значений
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green, // цвет при получении фокуса
            unfocusedBorderColor = Color.LightGray  // цвет при отсутствии фокуса
        )
    )
}







