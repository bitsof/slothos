package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Measurement

@Dao
interface MeasurementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(measurement: Measurement)

    @Update
    suspend fun update(measurement: Measurement)

    @Delete
    suspend fun delete(measurement: Measurement)

    @Query("SELECT * from measurements WHERE id = :id")
    fun getMeasurement(id: Int): Flow<Measurement>

    @Query("SELECT id from measurements WHERE name = :name LIMIT 1")
    fun getMeasurementId(name: String): Int?

    @Query("SELECT * from measurements ORDER BY name ASC")
    fun getAllMeasurements(): Flow<List<Measurement>>
}