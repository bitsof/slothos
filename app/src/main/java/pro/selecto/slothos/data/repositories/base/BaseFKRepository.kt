package pro.selecto.slothos.data.repositories.base

interface BaseFKRepository<T> {

    /**
     * Insert a given foreign key relationship
     */
    suspend fun insertFK(fkPair: T): Long

    /**
     * Delete a given foreign key relationship
     */
    suspend fun deleteFK(fkPair: T)
}