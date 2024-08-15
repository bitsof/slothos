package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "repetitions",
    foreignKeys = [
        ForeignKey(
            entity = Set::class,
            parentColumns = ["id"],
            childColumns = ["set_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exercise_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],)
@Serializable
class Repetition (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "planned_work") val plannedWork: Float,
    @ColumnInfo(name = "done_work") val doneWork: Float,
    @ColumnInfo(name = "set_id") val setId: Int,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int,
) {
}