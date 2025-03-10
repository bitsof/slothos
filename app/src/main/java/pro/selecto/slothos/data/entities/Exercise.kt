package pro.selecto.slothos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// based on the schema here: https://github.com/yuhonas/free-exercise-db/blob/main/schema.json
// also in the res folder

@Entity(tableName = "exercises")
@Parcelize
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name_id") val nameId: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "instructions") val instructions: String = "",
    ) : Parcelable {

}