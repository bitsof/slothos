package pro.selecto.slothos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onButtonClicked: () -> Unit,
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
    }
}