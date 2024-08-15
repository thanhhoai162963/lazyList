package com.example.lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.ui.theme.LazyColumnTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnTheme {
                DemoCoil()
            }
        }
    }
}

@Composable
fun DemoCoil() {
    AsyncImage(
        model = "https://img.idesign.vn/2023/02/idesign_logogg_1.jpg",
        contentDescription = "",
    )
    //cache

    Box(Modifier.fillMaxSize()) {
        SubcomposeAsyncImage( // load bat dong bo
            modifier = Modifier.align(Alignment.Center),
            model = ImageRequest.Builder(LocalContext.current).crossfade(true)
                .data("https://example.com/image.jpg").build(),
            loading = {
                CircularProgressIndicator()
            },
            error = {
                Text(text = "error !")
            },
            contentDescription = "stringResource(R.string.description)"
        )
    }
}

@Composable
fun DemoGrid() {
    val items = remember {
        mutableStateListOf(Demo())
    }
    for (i in 0..100) {
        items.add(Demo(content = "Android"))
    }
    /*  LazyVerticalGrid(columns = GridCells.Fixed(3)) {
          items(items, key = { it.id }) { it ->
              Text(text = it.content ?: "Android")
          }
      }*/
    LazyHorizontalGrid(rows = GridCells.Fixed(3)) {
        items(items, key = { it.id }) { it ->
            Text(text = it.content ?: "Android")
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val items = remember {
        mutableStateListOf(Demo())
    }
    for (i in 0..100) {
        items.add(Demo(content = "Android"))
    }

    Box(Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { items.add(1, Demo(content = "iOS")) }) {
            Text(text = "DEMO")
        }
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(text = "Header")
            }
            items(items, key = { it.id }) { it ->
                Text(text = it.content ?: "Android")
            }
        }
    }
}


data class Demo(val id: String = UUID.randomUUID().toString(), var content: String? = null) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun toString(): String {
        return super.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumnTheme {
        Greeting("Android")
    }
}