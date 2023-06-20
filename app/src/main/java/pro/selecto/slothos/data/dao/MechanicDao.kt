package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Mechanic

@Dao
interface MechanicDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mechanic: Mechanic)

    @Update
    suspend fun update(mechanic: Mechanic)

    @Delete
    suspend fun delete(mechanic: Mechanic)

    @Query("SELECT * from mechanics WHERE id = :id")
    fun getMechanic(id: Int): Flow<Mechanic>

    @Query("SELECT * from mechanics ORDER BY name ASC")
    fun getAllMechanics(): Flow<List<Mechanic>>
}