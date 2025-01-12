package pro.selecto.slothos.ui.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.selecto.slothos.data.SetDetails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSetScreen(
    onSetAdded: (SetDetails) -> Unit,
    navigateBack: () -> Unit,
) {
    var setName by remember { mutableStateOf("") }
    var setNotes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Add Set",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = setName,
            onValueChange = { setName = it},
            label = { Text("Set Name")},
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = setNotes,
            onValueChange = { setNotes = it },
            label = { Text("Set Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
//                val newSetDetails = SetDetails(
//                    set = Set(name = setName, description = setNotes),
//                    exerciseDetails = ExerciseDetails()
//                )
//                onSetAdded(newSetDetails)
                navigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Set")
        }
    }
}