package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "sets",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workout_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Set::class,
            parentColumns = ["id"],
            childColumns = ["set_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],)
@Serializable
class Set (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order") val order: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "notes") val description: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "workout_id") val workoutId: Int?,
    @ColumnInfo(name = "set_id") val setId: Int?,
) {

}