package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseMechanicFK

@Dao
interface ExerciseMechanicFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseMechanicFK: ExerciseMechanicFK): Long

    @Update
    suspend fun update(exerciseMechanicFK: ExerciseMechanicFK)

    @Delete
    suspend fun delete(exerciseMechanicFK: ExerciseMechanicFK)
}