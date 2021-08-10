package com.shubhamkumarwinner.composeconstraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.shubhamkumarwinner.composeconstraintlayout.ui.theme.ComposeConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeConstraintLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

//constraint layout
@Composable
fun ConstraintLayoutDemo() {
    ConstraintLayout(modifier = Modifier.size(350.dp)) {
        val (redBox, blueBox, yellowBox, text, greeting) = createRefs()
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {}
        )

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(redBox.end)
            }
        )

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                bottom.linkTo(blueBox.bottom)
                start.linkTo(blueBox.end, 20.dp)
            }
        )

        Text("Hello World", modifier = Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            start.linkTo(yellowBox.start)
        })

        Box(modifier = Modifier.constrainAs(greeting){
            bottom.linkTo(yellowBox.bottom)
            start.linkTo(yellowBox.end, 10.dp)
        }){
            Greeting(name = "Shubham")
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeConstraintLayoutTheme {
        ConstraintLayoutDemo()
    }
}