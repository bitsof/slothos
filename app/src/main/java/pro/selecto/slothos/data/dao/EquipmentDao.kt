package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Equipment

@Dao
interface EquipmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(equipment: Equipment): Long

    @Update
    suspend fun update(equipment: Equipment)

    @Delete
    suspend fun delete(equipment: Equipment)

    @Query("SELECT * from equipment WHERE id = :id")
    fun getEquipment(id: Int): Flow<Equipment>

    @Query("SELECT id from equipment WHERE name = :name LIMIT 1")
    fun getEquipmentId(name: String): Int?

    @Query("SELECT * from equipment ORDER BY name ASC")
    fun getAllEquipment(): Flow<List<Equipment>>

    @Query("""
        SELECT equipment.* FROM equipment 
        INNER JOIN exercise_equipment_fks
        ON equipment.id = exercise_equipment_fks.equipment_id 
        WHERE exercise_equipment_fks.exercise_id = :id
    """)
    fun getAllEquipmentMatchingExerciseID(id: Int): Flow<List<Equipment>>

}