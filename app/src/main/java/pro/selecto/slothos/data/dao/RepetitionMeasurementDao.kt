package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.RepetitionMeasurement

@Dao
interface RepetitionMeasurementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repetitionMeasurement: RepetitionMeasurement)

    @Update
    suspend fun update(repetitionMeasurement: RepetitionMeasurement)

    @Delete
    suspend fun delete(repetitionMeasurement: RepetitionMeasurement)
}