package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.Equipment

@Dao
interface EquipmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(equipment: Equipment)

    @Update
    suspend fun update(equipment: Equipment)

    @Delete
    suspend fun delete(equipment: Equipment)

    @Query("SELECT * from equipment WHERE id = :id")
    fun getEquipment(id: Int): Flow<Equipment>

    @Query("SELECT * from equipment ORDER BY name ASC")
    fun getAllEquipment(): Flow<List<Equipment>>
}