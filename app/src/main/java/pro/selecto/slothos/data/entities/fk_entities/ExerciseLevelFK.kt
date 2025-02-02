package pro.selecto.slothos.data.entities.fk_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.Level

@Entity(tableName = "exercise_level_fks",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exercise_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["level_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("exercise_id"),
        Index("level_id")
    ])
@Serializable
class ExerciseLevelFK(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "exercise_id") val exercise_id: Int,
    @ColumnInfo(name = "level_id") val level_id: Int,
) {
}