package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Category

/**
 * Repository that provides insert, update, delete, and retrieve of [Category] from a given data source.
 */
interface CategoryRepository {
    /**
     * Retrieve all the categories from the the given data source.
     */
    fun getAllCategoriesStream(): Flow<List<Category>>

    /**
     * Retrieve a category from the given data source that matches with the [id].
     */
    fun getCategoryStream(id: Int): Flow<Category?>

    /**
     * Insert category in the data source
     */
    suspend fun insertCategory(category: Category)

    /**
     * Delete exercise from the data source
     */
    suspend fun deleteCategory(category: Category)

    /**
     * Update exercise in the data source
     */
    suspend fun updateCategory(category: Category)
}