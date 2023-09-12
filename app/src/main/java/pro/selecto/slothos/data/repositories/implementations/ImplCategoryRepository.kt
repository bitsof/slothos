package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.CategoryDao
import pro.selecto.slothos.data.dao.ExerciseCategoryFKDao
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import javax.inject.Inject

class ImplCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val exerciseCategoryFKDao: ExerciseCategoryFKDao
) : CategoryRepository {
    override fun getAllCategoriesStream(): Flow<List<Category>> = categoryDao.getAllCategories()

    override fun getAllCategoriesMatchingIDStream(id: Int): Flow<List<Category>> = categoryDao.getAllCategoriesMatchingExerciseID(id = id)

    override fun getCategoryStream(id: Int): Flow<Category?> = categoryDao.getCategory(id)

    override suspend fun insertCategory(category: Category) = categoryDao.insert(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.delete(category)

    override suspend fun updateCategory(category: Category) = categoryDao.update(category)

    override suspend fun insertFK(fkPair: ExerciseCategoryFK) = exerciseCategoryFKDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseCategoryFK) = exerciseCategoryFKDao.delete(fkPair)

    }