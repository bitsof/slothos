package pro.selecto.slothos.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * from categories WHERE id = :id")
    fun getCategory(id: Int): Flow<Category>

    @Query("SELECT * from categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<Category>>

    @Query("""
        SELECT categories.* FROM categories 
        INNER JOIN exercise_category_fks
        ON categories.id = exercise_category_fks.category_id 
        WHERE exercise_category_fks.exercise_id = :id
    """)
    fun getAllCategoriesMatchingExerciseID(id: Int): Flow<List<Category>>
}