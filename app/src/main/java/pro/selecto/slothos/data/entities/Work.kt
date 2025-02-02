package pro.selecto.slothos.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.model.StandardMeasurementType

@Entity(tableName = "work",
    foreignKeys = [
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
    ])
@Parcelize
class Work(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order") val order: Int = 0,
    @ColumnInfo(name = "value") val value: Float = 0F,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "measurement_type") val measurementType: StandardMeasurementType = StandardMeasurementType.POUNDS,
    @ColumnInfo(name = "set_id") var setId: Int = 0,
) : Parcelable {

}