package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlin.collections.Set

@Entity(tableName = "repetition_measurements",
    foreignKeys = [
        ForeignKey(
            entity = Set::class,
            parentColumns = ["id"],
            childColumns = ["repetition_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],)
@Serializable
class RepetitionMeasurement (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "value") val order: Float,
    @ColumnInfo(name = "is_planned") val isPlanned: Boolean,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @ColumnInfo(name = "repetition_id") val repetitionId: Int,
) {
}