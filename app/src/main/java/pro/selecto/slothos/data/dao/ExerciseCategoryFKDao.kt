package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseCategoryFK

@Dao
interface ExerciseCategoryFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseCategoryFK: ExerciseCategoryFK)

    @Update
    suspend fun update(exerciseCategoryFK: ExerciseCategoryFK)

    @Delete
    suspend fun delete(exerciseCategoryFK: ExerciseCategoryFK)
}