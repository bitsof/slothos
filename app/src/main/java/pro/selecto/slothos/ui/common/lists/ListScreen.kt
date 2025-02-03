package pro.selecto.slothos.ui.common.lists

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pro.selecto.slothos.ui.features.exercise.list.ListMode

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    mode: ListMode,
    onAddClick: () -> Unit,
    showAddButton: (ListMode) -> Boolean = { it == ListMode.VIEW },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (showAddButton(mode)) {
                FloatingActionButton(
                    onClick = onAddClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    ) { padding ->
        content(padding)
    }
}