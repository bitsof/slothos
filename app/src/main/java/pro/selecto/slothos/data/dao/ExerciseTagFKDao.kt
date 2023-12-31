package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseTagFK

@Dao
interface ExerciseTagFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseTagFK: ExerciseTagFK)

    @Update
    suspend fun update(exerciseTagFK: ExerciseTagFK)

    @Delete
    suspend fun delete(exerciseTagFK: ExerciseTagFK)
}