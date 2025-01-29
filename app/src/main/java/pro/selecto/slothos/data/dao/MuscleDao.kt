package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Muscle

@Dao
interface MuscleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(muscle: Muscle): Long

    @Update
    suspend fun update(muscle: Muscle)

    @Delete
    suspend fun delete(muscle: Muscle)

    @Query("SELECT * from muscles WHERE id = :id")
    fun getMuscle(id: Int): Flow<Muscle>
    @Query("SELECT id from categories WHERE name = :name LIMIT 1")
    fun getCategoryId(name: String): Int?

    @Query("SELECT * from muscles ORDER BY name ASC")
    fun getAllMuscles(): Flow<List<Muscle>>
}