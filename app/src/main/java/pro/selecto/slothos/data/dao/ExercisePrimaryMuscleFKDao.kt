package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExercisePrimaryMuscleFK

@Dao
interface ExercisePrimaryMuscleFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercisePrimaryMuscleFK: ExercisePrimaryMuscleFK)

    @Update
    suspend fun update(exercisePrimaryMuscleFK: ExercisePrimaryMuscleFK)

    @Delete
    suspend fun delete(exercisePrimaryMuscleFK: ExercisePrimaryMuscleFK)
}