package pro.selecto.slothos.data.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
open class BaseForeignKeyRelation<P, C>(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    val parentId: P,
    val childId: C
)