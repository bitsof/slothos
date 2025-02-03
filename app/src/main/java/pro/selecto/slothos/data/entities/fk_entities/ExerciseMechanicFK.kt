package pro.selecto.slothos.data.entities.fk_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.Mechanic

@Entity(tableName = "exercise_mechanic_fks",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exercise_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Mechanic::class,
            parentColumns = ["id"],
            childColumns = ["mechanic_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("exercise_id"),
        Index("mechanic_id")
    ])
@Serializable
class ExerciseMechanicFK(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int,
    @ColumnInfo(name = "mechanic_id") val mechanicId: Int,
) {
}