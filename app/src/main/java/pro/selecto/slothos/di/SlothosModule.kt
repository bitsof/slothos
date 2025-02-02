package pro.selecto.slothos.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import pro.selecto.slothos.data.services.ExerciseDetailsService
import pro.selecto.slothos.data.services.SetDetailsService
import pro.selecto.slothos.data.services.WorkDetailsService
import pro.selecto.slothos.data.WorkoutDatabase
import pro.selecto.slothos.data.services.WorkoutDetailsService
import pro.selecto.slothos.data.dao.CategoryDao
import pro.selecto.slothos.data.dao.EquipmentDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseCategoryFKDao
import pro.selecto.slothos.data.dao.ExerciseDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseEquipmentFKDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseForceFKDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseLevelFKDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseMechanicFKDao
import pro.selecto.slothos.data.dao.fkdao.ExercisePrimaryMuscleFKDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseSecondaryMuscleFKDao
import pro.selecto.slothos.data.dao.fkdao.ExerciseTagFKDao
import pro.selecto.slothos.data.dao.ForceDao
import pro.selecto.slothos.data.dao.LevelDao
import pro.selecto.slothos.data.dao.MeasurementDao
import pro.selecto.slothos.data.dao.MechanicDao
import pro.selecto.slothos.data.dao.MuscleDao
import pro.selecto.slothos.data.dao.SetDao
import pro.selecto.slothos.data.dao.TagDao
import pro.selecto.slothos.data.dao.WorkDao
import pro.selecto.slothos.data.dao.WorkoutDao
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.Measurement
import pro.selecto.slothos.data.entities.Workout
import pro.selecto.slothos.data.repositories.implementations.ImplCategoryRepository
import pro.selecto.slothos.data.repositories.implementations.ImplEquipmentRepository
import pro.selecto.slothos.data.repositories.implementations.ImplExerciseRepository
import pro.selecto.slothos.data.repositories.implementations.ImplMeasurementRepository
import pro.selecto.slothos.data.repositories.implementations.ImplSetRepository
import pro.selecto.slothos.data.repositories.implementations.ImplWorkRepository
import pro.selecto.slothos.data.repositories.implementations.ImplWorkoutRepository
import pro.selecto.slothos.data.repositories.base.BaseRepository
import pro.selecto.slothos.data.repositories.base.CategoryRepository
import pro.selecto.slothos.data.repositories.base.EquipmentRepository
import pro.selecto.slothos.data.repositories.interfaces.SetRepository
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository
import pro.selecto.slothos.ui.core.MainActivity
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
            .fallbackToDestructiveMigration()//.createFromAsset("database/exercise_database.db")
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

    @Provides
    fun provideWorkDao(workoutDatabase: WorkoutDatabase): WorkDao {
        return workoutDatabase.workDao()
    }

    @Provides
    fun provideWorkoutDao(workoutDatabase: WorkoutDatabase): WorkoutDao {
        return workoutDatabase.workoutDao()
    }

    @Provides
    fun provideMeasurementDao(workoutDatabase: WorkoutDatabase): MeasurementDao {
        return workoutDatabase.measurementDao()
    }

    @Provides
    fun provideSetDao(workoutDatabase: WorkoutDatabase): SetDao {
        return workoutDatabase.setDao()
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
    fun provideWorkRepository(
        workDao: WorkDao
    ): WorkRepository {
        return ImplWorkRepository(workDao)
    }

    @Provides
    fun provideSetRepository(
        setDao: SetDao
    ): SetRepository {
        return ImplSetRepository(setDao)
    }

    @Provides
    fun provideWorkoutRepository(
        workoutDao: WorkoutDao
    ): BaseRepository<Workout> {
        return ImplWorkoutRepository(workoutDao)
    }

    @Provides
    fun provideMeasurementRepository(
        measurementDao: MeasurementDao
    ): BaseRepository<Measurement> {
        return ImplMeasurementRepository(measurementDao)
    }

    @Provides
    fun provideExerciseDetailsService(
        exerciseRepository: BaseRepository<Exercise>,
        categoryRepository: CategoryRepository,
        equipmentRepository: EquipmentRepository,
    ): ExerciseDetailsService {
        return ExerciseDetailsService(Dispatchers.Default, exerciseRepository, categoryRepository, equipmentRepository)
    }

    @Provides
    fun provideWorkDetailsService(
        workRepository: WorkRepository,
        measurementRepository: BaseRepository<Measurement>,
    ): WorkDetailsService {
        return WorkDetailsService(Dispatchers.Default, workRepository, measurementRepository)
    }

    @Provides
    fun provideSetDetailsService(
        setRepository: SetRepository,
        workDetailsService: WorkDetailsService,
        exerciseDetailsService: ExerciseDetailsService
    ): SetDetailsService {
        return SetDetailsService(Dispatchers.Default, setRepository, workDetailsService, exerciseDetailsService)
    }

    @Provides
    fun provideWorkoutDetailService(
        setDetailsService: SetDetailsService,
        workoutRepository: BaseRepository<Workout>
    ): WorkoutDetailsService {
        return WorkoutDetailsService(Dispatchers.Default, workoutRepository, setDetailsService)
    }

    @Provides
    fun provideJsonHandler(
        context: Context
    ): JsonHandler {
        return JsonHandler(context)
    }

}