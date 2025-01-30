package pro.selecto.slothos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sets",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workout_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Set::class,
            parentColumns = ["id"],
            childColumns = ["set_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exercise_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("workout_id"),
        Index("set_id"),
        Index("exercise_id"),
    ])
@Parcelize
class Set (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order") val order: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "notes") val notes: String = "",
    @ColumnInfo(name = "date") val date: Long = 0,
    @ColumnInfo(name = "workout_id") var workoutId: Int? = null,
    @ColumnInfo(name = "set_id") var setId: Int? = null,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int = 0,
) : Parcelable {

}