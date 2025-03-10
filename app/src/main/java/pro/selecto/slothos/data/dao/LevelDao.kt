package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Level

@Dao
interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(level: Level): Long

    @Update
    suspend fun update(level: Level)

    @Delete
    suspend fun delete(level: Level)

    @Query("SELECT * from levels WHERE id = :id")
    fun getLevel(id: Int): Flow<Level>

    @Query("SELECT id from levels WHERE name = :name LIMIT 1")
    fun getLevelId(name: String): Int?

    @Query("SELECT * from levels ORDER BY name ASC")
    fun getAllLevels(): Flow<List<Level>>
}