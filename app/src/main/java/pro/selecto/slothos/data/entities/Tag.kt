package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

// If a user wants to further sort exercises they can generate
// and store their own tags to sort by or generate workouts.
// By default the only tag is the one we use for our curated workouts
// from the large database.

@Entity(tableName = "tags")
@Serializable
class Tag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
) {

}