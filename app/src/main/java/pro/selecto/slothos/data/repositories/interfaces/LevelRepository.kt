package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.ExerciseLevelFK
import pro.selecto.slothos.data.entities.Level

/**
 * Repository that provides insert, update, delete, and retrieve of [Level] from a given data source.
 */
interface LevelRepository {
    /**
     * Retrieve all the levels from the the given data source.
     */
    fun getAllLevelsStream(): Flow<List<Level>>

    /**
     * Retrieve an level from the given data source that matches with the [id].
     */
    fun getLevelStream(id: Int): Flow<Level?>

    /**
     * Insert level in the data source
     */
    suspend fun insertLevel(level: Level)

    /**
     * Delete level from the data source
     */
    suspend fun deleteLevel(level: Level)

    /**
     * Update level in the data source
     */
    suspend fun updateLevel(level: Level)

    /**
     * Insert a given foreign key relationship
     */
    suspend fun insertFK(fkPair: ExerciseLevelFK)

    /**
     * Delete a given foreign key relationship
     */
    suspend fun deleteFK(fkPair: ExerciseLevelFK)
}