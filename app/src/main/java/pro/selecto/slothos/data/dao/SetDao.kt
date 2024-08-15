package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.Set

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(set: Set)

    @Update
    suspend fun update(set: Set)

    @Delete
    suspend fun delete(set: Set)

    // add query to get sets for a given workout
}