package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Force

@Dao
interface ForceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(force: Force): Long

    @Update
    suspend fun update(force: Force)

    @Delete
    suspend fun delete(force: Force)

    @Query("SELECT * from forces WHERE id = :id")
    fun getForce(id: Int): Flow<Force>

    @Query("SELECT id from forces WHERE name = :name LIMIT 1")
    fun getForceId(name: String): Int?

    @Query("SELECT * from forces ORDER BY name ASC")
    fun getAllForces(): Flow<List<Force>>
}