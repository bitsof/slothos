package pro.selecto.slothos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onButtonClicked: () -> Unit,
    onButtonClicked2: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = "Checking",
            style = MaterialTheme.typography.headlineSmall,
        )
        Button(
            onClick = onButtonClicked,
        ) {

        }
        Button(
            onClick = onButtonClicked2,
        ) {

        }
    }
}