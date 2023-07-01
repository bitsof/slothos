package pro.selecto.slothos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.dao.CategoryDao
import pro.selecto.slothos.data.dao.EquipmentDao
import pro.selecto.slothos.data.dao.ExerciseCategoryFKDao
import pro.selecto.slothos.data.dao.ExerciseDao
import pro.selecto.slothos.data.dao.ExerciseEquipmentFKDao
import pro.selecto.slothos.data.dao.ExerciseForceFKDao
import pro.selecto.slothos.data.dao.ExerciseLevelFKDao
import pro.selecto.slothos.data.dao.ExerciseMechanicFKDao
import pro.selecto.slothos.data.dao.ExercisePrimaryMuscleFKDao
import pro.selecto.slothos.data.dao.ExerciseSecondaryMuscleFKDao
import pro.selecto.slothos.data.dao.ExerciseTagFKDao
import pro.selecto.slothos.data.dao.ForceDao
import pro.selecto.slothos.data.dao.LevelDao
import pro.selecto.slothos.data.dao.MechanicDao
import pro.selecto.slothos.data.dao.MuscleDao
import pro.selecto.slothos.data.dao.TagDao
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK
import pro.selecto.slothos.data.entities.ExerciseForceFK
import pro.selecto.slothos.data.entities.ExerciseLevelFK
import pro.selecto.slothos.data.entities.ExerciseMechanicFK
import pro.selecto.slothos.data.entities.ExercisePrimaryMuscleFK
import pro.selecto.slothos.data.entities.ExerciseSecondaryMuscleFK
import pro.selecto.slothos.data.entities.ExerciseTagFK
import pro.selecto.slothos.data.entities.Force
import pro.selecto.slothos.data.entities.Level
import pro.selecto.slothos.data.entities.Mechanic
import pro.selecto.slothos.data.entities.Muscle
import pro.selecto.slothos.data.entities.Tag
import pro.selecto.slothos.utils.getData

/**
 * Database class with a singleton Instance object.
 */
// Increment version number by 1 when database is changed !!!
@Database(
    entities = [Category::class,
        Equipment::class,
        Exercise::class,
        Force::class,
        Level::class,
        Mechanic::class,
        Muscle::class,
        Tag::class,
        ExerciseCategoryFK::class,
        ExerciseEquipmentFK::class,
        ExerciseForceFK::class,
        ExerciseLevelFK::class,
        ExerciseMechanicFK::class,
        ExercisePrimaryMuscleFK::class,
        ExerciseSecondaryMuscleFK::class,
        ExerciseTagFK::class],
    version = 1,
    exportSchema = true
)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun forceDao(): ForceDao
    abstract fun levelDao(): LevelDao
    abstract fun mechanicDao(): MechanicDao
    abstract fun muscleDao(): MuscleDao
    abstract fun tagDao(): TagDao
    abstract fun exerciseCategoryFKDao(): ExerciseCategoryFKDao
    abstract fun exerciseEquipmentFKDao(): ExerciseEquipmentFKDao
    abstract fun exerciseForceFKDao(): ExerciseForceFKDao
    abstract fun exerciseLevelFKDao(): ExerciseLevelFKDao
    abstract fun exerciseMechanicFKDao(): ExerciseMechanicFKDao
    abstract fun exercisePrimaryMuscleFKDao(): ExercisePrimaryMuscleFKDao
    abstract fun exerciseSecondaryMuscleFKDao(): ExerciseSecondaryMuscleFKDao
    abstract fun exerciseTagFKDao(): ExerciseTagFKDao

    companion object {
        @Volatile
        private var Instance: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_database")
                    .build()
                    .also { Instance = it }
                    .also { it.populateInitialData() }
            }
        }
    }

    private fun populateInitialData(){

        val exercises = getData() // Assuming this retrieves an exercise object

        CoroutineScope(Dispatchers.IO).launch {
            for (exercise in exercises)
                Instance?.exerciseDao()?.insert(exercise) // Insert the exercise using the DAO's insert() method
        }

        println("Final exercise count " + Instance?.exerciseDao()?.count())
        // get json data from website
        // parse json data to list of exercises, fks, etc.
        // insert each starting with individual objects and ending with fks
    }
}