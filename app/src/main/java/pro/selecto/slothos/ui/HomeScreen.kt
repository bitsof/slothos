package pro.selecto.slothos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onButtonClicked: () -> Unit,
    onButtonClicked2: () -> Unit,
    onButtonClicked3: () -> Unit,
    onButtonClicked5: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Home Page",
            style = MaterialTheme.typography.headlineSmall,
        )
        Button(
            onClick = onButtonClicked,
        ) {
            Text("Exercise List")
        }
        Button(
            onClick = onButtonClicked2,
        ) {
            Text("Insert Exercise")
        }

        Button(
            onClick = onButtonClicked3,
        ) {
            Text("Insert Workout")
        }

        Button(
            onClick = onButtonClicked5,
        ) {
            Text("Workout List")
        }
    }
}