package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK

@Dao
interface ExerciseEquipmentFKDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseEquipmentFK: ExerciseEquipmentFK)

    @Update
    suspend fun update(exerciseEquipmentFK: ExerciseEquipmentFK)

    @Delete
    suspend fun delete(exerciseEquipmentFK: ExerciseEquipmentFK)
}