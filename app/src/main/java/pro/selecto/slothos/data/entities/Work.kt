package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "work",
    foreignKeys = [
        ForeignKey(
            entity = Measurement::class,
            parentColumns = ["id"],
            childColumns = ["measurement_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Set::class,
            parentColumns = ["id"],
            childColumns = ["set_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index("set_id"),
        Index("measurement_id"),
    ])
@Serializable
class Work(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "value") val name: Float = 0F,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "measurement_id") val measurementId: Int,
    @ColumnInfo(name = "set_id") val setId: Int,
) {

}