package pro.selecto.slothos.data.dao.fkdao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseForceFK

@Dao
interface ExerciseForceFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseForceFK: ExerciseForceFK): Long

    @Update
    suspend fun update(exerciseForceFK: ExerciseForceFK)

    @Delete
    suspend fun delete(exerciseForceFK: ExerciseForceFK)
}