package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.Repetition

@Dao
interface RepetitionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repetition: Repetition)

    @Update
    suspend fun update(repetition: Repetition)

    @Delete
    suspend fun delete(repetition: Repetition)
}