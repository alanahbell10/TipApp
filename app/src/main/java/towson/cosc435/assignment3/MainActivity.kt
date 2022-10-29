package towson.cosc435.assignment3

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import towson.cosc435.assignment3.ui.theme.Assignment3Theme
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.time.toDuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var check = remember { mutableStateOf("") }
    var selectedTip = remember { mutableStateOf(0.0)}

    var finalCheck: Double = check.value?.toDoubleOrNull() ?: 0.0




    Column {
        Title()
        Header("Enter your check amount:")
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("$", fontSize = 32.sp)
            TextField(value = check.value,
                onValueChange = { check.value = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Header("Select your tip amount:")
        //TipButtons()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {


            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedTip.value == 0.10) Color(0xFF6200EE) else Color.Gray),
                onClick = {selectedTip.value = 0.10 },
                modifier = Modifier.padding(4.dp)) {
                Text(text = "10%")
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedTip.value == 0.20) Color(0xFF6200EE) else Color.Gray),
                onClick = { selectedTip.value = 0.20},
                modifier = Modifier.padding(4.dp) ) {
                Text(text = "20%")
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedTip.value == 0.30) Color(0xFF6200EE) else Color.Gray),
                onClick = { selectedTip.value = 0.30},
                modifier = Modifier.padding(4.dp) ) {
                Text(text = "30%")
            }
        }
        ChangeValues(selectedTip.value, finalCheck)



    }
}
@Composable
fun ChangeValues(tip: Double, check: Double) {

    var finalTip by remember { mutableStateOf(0.0) }
    var finalCheck by remember { mutableStateOf(0.0) }

    var checkVal = ((finalCheck * 100.0).roundToInt() / 100.0).toString()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var tipVal = ((finalTip * 100.0).roundToInt() / 100.0)
            Text(text = "Tip: $$tipVal", fontSize = 24.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (check.equals("")) {
                Text(text = "Total: ERROR", fontSize = 24.sp)
            } else {
                Text(text = "Total: $$checkVal", fontSize = 24.sp)
            }
        }
        Button(onClick = {
                finalTip = tip * check
            if (check.equals(" ")) {
                finalCheck = 0.0
            } else {
                finalCheck = check + finalTip
            }

        }) {
            Text(text = "Calculate")
        }
    }
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tip Calculator", fontSize = 32.sp)
    }
}

@Composable
fun Header(header: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$header", fontSize = 16.sp)
    }
}

@Composable
fun ErrorResult() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("ERROR")
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment3Theme {
        Title()
    }
}