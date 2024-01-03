package pro.selecto.slothos.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import pro.selecto.slothos.data.ExerciseDetailsService
import pro.selecto.slothos.data.WorkoutDatabase
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
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.implementations.ImplCategoryRepository
import pro.selecto.slothos.data.repositories.implementations.ImplEquipmentRepository
import pro.selecto.slothos.data.repositories.implementations.ImplExerciseRepository
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import pro.selecto.slothos.data.repositories.interfaces.EquipmentRepository
import pro.selecto.slothos.ui.MainActivity
import pro.selecto.slothos.utils.JsonHandler
import javax.inject.Singleton

@Module
class AppModule(private val application: MainActivity) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = application.applicationContext
}

@Module
object WorkoutModule {

    // This generates a database from the READ-ONLY assets folder
    @Provides
    @Singleton
    fun provideWorkoutDatabase(appContext: Context): WorkoutDatabase {
        return Room.databaseBuilder(
            appContext,
            WorkoutDatabase::class.java,
            "workout_database.db"
        )
            .createFromAsset("database/exercise_database.db")
            .build()
    }
}

@Module
object DaoModule {
    @Provides
    fun provideCategoryDao(workoutDatabase: WorkoutDatabase): CategoryDao {
        return workoutDatabase.categoryDao()
    }

    @Provides
    fun provideEquipmentDao(workoutDatabase: WorkoutDatabase): EquipmentDao {
        return workoutDatabase.equipmentDao()
    }

    @Provides
    fun provideExerciseDao(workoutDatabase: WorkoutDatabase): ExerciseDao {
        return workoutDatabase.exerciseDao()
    }

    @Provides
    fun provideForceDao(workoutDatabase: WorkoutDatabase): ForceDao {
        return workoutDatabase.forceDao()
    }

    @Provides
    fun provideLevelDao(workoutDatabase: WorkoutDatabase): LevelDao {
        return workoutDatabase.levelDao()
    }

    @Provides
    fun provideMechanicDao(workoutDatabase: WorkoutDatabase): MechanicDao {
        return workoutDatabase.mechanicDao()
    }

    @Provides
    fun provideMuscleDao(workoutDatabase: WorkoutDatabase): MuscleDao {
        return workoutDatabase.muscleDao()
    }

    @Provides
    fun provideTagDao(workoutDatabase: WorkoutDatabase): TagDao {
        return workoutDatabase.tagDao()
    }

    @Provides
    fun provideExerciseCategoryFKDao(workoutDatabase: WorkoutDatabase): ExerciseCategoryFKDao {
        return workoutDatabase.exerciseCategoryFKDao()
    }

    @Provides
    fun provideExerciseEquipmentFKDao(workoutDatabase: WorkoutDatabase): ExerciseEquipmentFKDao {
        return workoutDatabase.exerciseEquipmentFKDao()
    }

    @Provides
    fun provideExerciseForceFKDao(workoutDatabase: WorkoutDatabase): ExerciseForceFKDao {
        return workoutDatabase.exerciseForceFKDao()
    }

    @Provides
    fun provideExerciseLevelFKDao(workoutDatabase: WorkoutDatabase): ExerciseLevelFKDao {
        return workoutDatabase.exerciseLevelFKDao()
    }
    @Provides
    fun provideExerciseMechanicFKDao(workoutDatabase: WorkoutDatabase): ExerciseMechanicFKDao {
        return workoutDatabase.exerciseMechanicFKDao()
    }

    @Provides
    fun provideExercisePrimaryMuscleFKDao(workoutDatabase: WorkoutDatabase): ExercisePrimaryMuscleFKDao {
        return workoutDatabase.exercisePrimaryMuscleFKDao()
    }

    @Provides
    fun provideExerciseSecondaryMuscleFKDao(workoutDatabase: WorkoutDatabase): ExerciseSecondaryMuscleFKDao {
        return workoutDatabase.exerciseSecondaryMuscleFKDao()
    }

    @Provides
    fun provideExerciseTagFKDao(workoutDatabase: WorkoutDatabase): ExerciseTagFKDao {
        return workoutDatabase.exerciseTagFKDao()
    }

}

@Module
object RepositoryModule {
    @Provides
    fun provideCategoryRepository(
        categoryDao: CategoryDao,
        categoryFKDao: ExerciseCategoryFKDao
    ): CategoryRepository {
        return ImplCategoryRepository(categoryDao, categoryFKDao)
    }

    @Provides
    fun provideExerciseRepository(
        exerciseDao: ExerciseDao
    ): BaseRepository<Exercise> {
        return ImplExerciseRepository(exerciseDao)
    }

    @Provides
    fun provideEquipmentRepository(
        equipmentDao: EquipmentDao,
        equipmentFKDao: ExerciseEquipmentFKDao
    ): EquipmentRepository {
        return ImplEquipmentRepository(equipmentDao, equipmentFKDao)
    }

    @Provides
    fun provideExerciseDetailsService(
        exerciseRepository: BaseRepository<Exercise>,
        categoryRepository: CategoryRepository,
        equipmentRepository: EquipmentRepository,
    ): ExerciseDetailsService {
        return ExerciseDetailsService(exerciseRepository, categoryRepository, equipmentRepository)
    }

    @Provides
    fun provideJsonHandler(
        context: Context
    ): JsonHandler {
        return JsonHandler(context)
    }

}