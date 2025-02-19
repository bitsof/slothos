package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Tag

@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tag: Tag): Long

    @Update
    suspend fun update(tag: Tag)

    @Delete
    suspend fun delete(tag: Tag)

    @Query("SELECT * from tags WHERE id = :id")
    fun getTag(id: Int): Flow<Tag>

    @Query("SELECT id from tags WHERE name = :name LIMIT 1")
    fun getTagId(name: String): Int?

    @Query("SELECT * from tags ORDER BY name ASC")
    fun getAllTags(): Flow<List<Tag>>
}