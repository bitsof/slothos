package pro.selecto.slothos.data.dao.fkdao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.fk_entities.ExerciseSecondaryMuscleFK

@Dao
interface ExerciseSecondaryMuscleFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK): Long

    @Update
    suspend fun update(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK)

    @Delete
    suspend fun delete(exerciseSecondaryMuscleFK: ExerciseSecondaryMuscleFK)
}