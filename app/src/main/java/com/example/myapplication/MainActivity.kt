package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(

    name: String, modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Text(text = "Hello $name!")
        Row {
            Button(onClick = { viewModel.getThreads() }) {
                Text("click me")
            }
            Button(onClick = { viewModel.refreshThreads() }) {
                Text("click me2")
            }
        }
        Greeting2()

    }
}

@Composable
fun Greeting2(viewModel: AnotherViewModel = hiltViewModel()) {
    Column {
        Text(text = "Hello")
        Button(onClick = { viewModel.readToken2() }) {
            Text("click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}