package pro.selecto.slothos.ui.features

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import pro.selecto.slothos.ui.features.exercise.details.ExerciseDetailsViewModel
import pro.selecto.slothos.ui.features.exercise.insert.InsertExerciseViewModel
import pro.selecto.slothos.ui.features.exercise.list.ExerciseListViewModel
import pro.selecto.slothos.ui.features.workout.details.DisplayWorkoutViewModel
import pro.selecto.slothos.ui.features.workout.insert.InsertWorkoutViewModel
import pro.selecto.slothos.ui.features.set.insert.AddSetViewModel
import pro.selecto.slothos.ui.features.work.insert.AddWorkViewModel
import pro.selecto.slothos.ui.features.workout.list.WorkoutListViewModel
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

interface AssistedSavedStateViewModelFactory<T: ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}

// Refer to https://github.com/android/architecture-samples/blob/dev-dagger/app/src/main/java/com/example/android/architecture/blueprints/todoapp/di/ViewModelFactory.kt
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>,
    private val assistedFactories: @JvmSuppressWildcards Map<Class<out ViewModel>, AssistedSavedStateViewModelFactory<out ViewModel>>,
) : AbstractSavedStateViewModelFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val assistedFactory = findAssistedFactory(modelClass)
        if (assistedFactory != null) {
            @Suppress("UNCHECKED_CAST")
            return assistedFactory.create(handle) as T
        }
        val creator = findCreator(modelClass)
            ?: throw IllegalArgumentException("Unknown model class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun <T : ViewModel> findAssistedFactory(modelClass: Class<T>): AssistedSavedStateViewModelFactory<out ViewModel>? {
        return assistedFactories[modelClass] ?: assistedFactories.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value
    }

    private fun <T: ViewModel> findCreator(modelClass: Class<T>): Provider<out ViewModel>? {
        return creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value
    }
}

@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseListViewModel::class)
    abstract fun bindExerciseListViewModel(viewModel: ExerciseListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InsertExerciseViewModel::class)
    abstract fun bindInsertExerciseViewModel(factory: InsertExerciseViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseDetailsViewModel::class)
    abstract fun bindExerciseDetailsViewModel(factory: ExerciseDetailsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(InsertWorkoutViewModel::class)
    abstract fun bindInsertWorkoutViewModel(factory: InsertWorkoutViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(DisplayWorkoutViewModel::class)
    abstract fun bindDisplayWorkoutViewModel(factory: DisplayWorkoutViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(WorkoutListViewModel::class)
    abstract fun bindWorkoutListViewModel(viewModel: WorkoutListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddSetViewModel::class)
    abstract fun binAddSetViewModel(viewModel: AddSetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddWorkViewModel::class)
    abstract fun bindAddWorkViewModel(viewModel: AddWorkViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)