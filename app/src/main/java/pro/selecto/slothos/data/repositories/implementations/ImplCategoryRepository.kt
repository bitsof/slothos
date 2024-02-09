package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.CategoryDao
import pro.selecto.slothos.data.dao.ExerciseCategoryFKDao
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.repositories.interfaces.BaseFKRepository
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import pro.selecto.slothos.data.repositories.interfaces.RelatedToExerciseRepository
import javax.inject.Inject

class ImplCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val exerciseCategoryFKDao: ExerciseCategoryFKDao
) : CategoryRepository {
    override suspend fun getAllEntitiesStream(): Flow<List<Category>> = categoryDao.getAllCategories()

    override suspend fun getAllEntitiesMatchingIdStream(id: Int): Flow<List<Category>> = categoryDao.getAllCategoriesMatchingExerciseID(id = id)

    override suspend fun getEntityById(id: Int): Flow<Category?> = categoryDao.getCategory(id)

    override suspend fun getEntityIdByName(name: String): Int? = categoryDao.getCategoryId(name)

    override suspend fun insert(entity: Category) = categoryDao.insert(entity)

    override suspend fun delete(entity: Category) = categoryDao.delete(entity)

    override suspend fun update(entity: Category) = categoryDao.update(entity)

    override suspend fun insertFK(fkPair: ExerciseCategoryFK) = exerciseCategoryFKDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseCategoryFK) = exerciseCategoryFKDao.delete(fkPair)

    }