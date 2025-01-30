package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Work

@Dao
interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(work: Work): Long

    @Update
    suspend fun update(workout: Work)

    @Delete
    suspend fun delete(workout: Work)

    @Query("SELECT * from work WHERE id = :id")
    fun getWork(id: Int): Flow<Work>

    @Query("SELECT * from work ORDER BY id ASC")
    fun getAllWork(): Flow<List<Work>>

    @Query("SELECT * FROM work WHERE set_id = :id")
    fun getAllWorkMatchingSet(id: Int): Flow<List<Work>>
}