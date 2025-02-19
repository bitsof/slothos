package pro.selecto.slothos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "workouts")
@Parcelize
class Workout (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String ="",
    @ColumnInfo(name = "notes") val notes: String = "",
    @ColumnInfo(name = "description") val description:String = "",
    @ColumnInfo(name = "date") val date: Long = 0,
) : Parcelable {
}