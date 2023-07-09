package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.ExerciseTagFK
import pro.selecto.slothos.data.entities.Tag

/**
 * Repository that provides insert, update, delete, and retrieve of [Tag] from a given data source.
 */
interface TagRepository {
    /**
     * Retrieve all the tags from the the given data source.
     */
    fun getAllTagsStream(): Flow<List<Tag>>

    /**
     * Retrieve an tag from the given data source that matches with the [id].
     */
    fun getTagStream(id: Int): Flow<Tag?>

    /**
     * Insert tag in the data source
     */
    suspend fun insertTag(tag: Tag)

    /**
     * Delete tag from the data source
     */
    suspend fun deleteTag(tag: Tag)

    /**
     * Update tag in the data source
     */
    suspend fun updateTag(tag: Tag)

    /**
     * Insert a given foreign key relationship
     */
    suspend fun insertFK(fkPair: ExerciseTagFK)

    /**
     * Delete a given foreign key relationship
     */
    suspend fun deleteFK(fkPair: ExerciseTagFK)
}