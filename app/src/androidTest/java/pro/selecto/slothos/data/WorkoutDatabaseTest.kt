package pro.selecto.slothos.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class WorkoutDatabaseTest {
    private lateinit var context: Context

    @Before
    fun createDb() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testGetInstance() {
        val database = WorkoutDatabase.getDatabase(context)
        assertNotNull(database)
    }

    @Test
    fun testDAOs() {
        val database = WorkoutDatabase.getDatabase(context)
        val categoryDao = database.categoryDao()
        val equipmentDao = database.equipmentDao()
        val exerciseDao = database.exerciseDao()
        val forceDao = database.forceDao()
        val levelDao = database.levelDao()
        val mechanicDao = database.mechanicDao()
        val muscleDao = database.muscleDao()
        val tagDao = database.tagDao()
        val exerciseCategoryFKDao = database.exerciseCategoryFKDao()
        val exerciseEquipmentFKDao = database.exerciseEquipmentFKDao()
        val exerciseForceFKDao = database.exerciseForceFKDao()
        val exerciseLevelFKDao = database.exerciseLevelFKDao()
        val exerciseMechanicFKDao = database.exerciseMechanicFKDao()
        val exercisePrimaryMuscleFKDao = database.exercisePrimaryMuscleFKDao()
        val exerciseSecondaryMuscleFKDao = database.exerciseSecondaryMuscleFKDao()
        val exerciseTagFKDao = database.exerciseTagFKDao()
        assertNotNull(categoryDao)
        assertNotNull(equipmentDao)
        assertNotNull(exerciseDao)
        assertNotNull(forceDao)
        assertNotNull(levelDao)
        assertNotNull(mechanicDao)
        assertNotNull(muscleDao)
        assertNotNull(tagDao)
        assertNotNull(exerciseCategoryFKDao)
        assertNotNull(exerciseEquipmentFKDao)
        assertNotNull(exerciseForceFKDao)
        assertNotNull(exerciseLevelFKDao)
        assertNotNull(exerciseMechanicFKDao)
        assertNotNull(exercisePrimaryMuscleFKDao)
        assertNotNull(exerciseSecondaryMuscleFKDao)
        assertNotNull(exerciseTagFKDao)
    }
}