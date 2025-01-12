package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Set

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(set: Set)

    @Update
    suspend fun update(set: Set)

    @Delete
    suspend fun delete(set: Set)

    @Query("SELECT * from sets WHERE id = :id")
    fun getSet(id: Int): Flow<Set>

    @Query("SELECT id from sets WHERE name = :name LIMIT 1")
    fun getSetId(name: String): Int?

    @Query("SELECT * from sets ORDER BY name ASC")
    fun getAllSets(): Flow<List<Set>>

    @Query("SELECT * FROM sets WHERE set_id = :id")
    fun getAllSetsMatchingSetId(id: Int): Flow<List<Set>>

    @Query("SELECT * FROM sets WHERE workout_id = :id")
    fun getAllSetsMatchingWorkoutId(id:Int): Flow<List<Set>>
}