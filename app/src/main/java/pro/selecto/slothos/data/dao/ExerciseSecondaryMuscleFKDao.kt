package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseSecondaryMuscleFK

@Dao
interface ExerciseSecondaryMuscleFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK)

    @Update
    suspend fun update(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK)

    @Delete
    suspend fun delete(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK)
}