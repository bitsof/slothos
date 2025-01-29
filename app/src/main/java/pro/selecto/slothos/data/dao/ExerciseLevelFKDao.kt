package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseLevelFK

@Dao
interface ExerciseLevelFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseLevelFK: ExerciseLevelFK): Long

    @Update
    suspend fun update(exerciseLevelFK: ExerciseLevelFK)

    @Delete
    suspend fun delete(exerciseLevelFK: ExerciseLevelFK)
}