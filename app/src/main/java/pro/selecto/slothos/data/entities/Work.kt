package pro.selecto.slothos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.StandardMeasurementType

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
@Parcelize
class Work(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order") val order: Int = 0,
    @ColumnInfo(name = "value") val value: Float = 0F,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "measurement_type") val measurementType: StandardMeasurementType = StandardMeasurementType.POUNDS,
    @ColumnInfo(name = "measurement_id") val measurementId: Int,
    @ColumnInfo(name = "set_id") val setId: Int,
) : Parcelable {

}