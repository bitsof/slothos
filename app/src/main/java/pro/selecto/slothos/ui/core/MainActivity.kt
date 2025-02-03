package pro.selecto.slothos.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.repositories.implementations.ImplCategoryRepository
import pro.selecto.slothos.di.AppModule
import pro.selecto.slothos.di.DaoModule
import pro.selecto.slothos.di.RepositoryModule
import pro.selecto.slothos.di.WorkoutModule
import pro.selecto.slothos.ui.features.ViewModelBuilderModule
import pro.selecto.slothos.ui.features.ViewModelFactory
import pro.selecto.slothos.ui.features.exercise.details.ExerciseDetailsViewModel
import pro.selecto.slothos.ui.features.exercise.insert.InsertExerciseViewModel
import pro.selecto.slothos.ui.features.exercise.list.ExerciseListViewModel
import pro.selecto.slothos.ui.theme.SlothosTheme
import javax.inject.Inject
import javax.inject.Singleton

@Component(modules = [AppModule::class, WorkoutModule::class, DaoModule::class, RepositoryModule::class, ViewModelBuilderModule::class])
@Singleton
interface AppComponent {
    fun inject(app: MainActivity)
    fun inject(exerciseDetailsViewModel: ExerciseDetailsViewModel)
    fun inject(exerciseListViewModel: ExerciseListViewModel)
    fun inject(insertExerciseViewModel: InsertExerciseViewModel)
    fun repository(): ImplCategoryRepository
    fun viewModelsFactory(): ViewModelFactory

}

class MainActivity : ComponentActivity() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        CoroutineScope(Dispatchers.IO).launch {
        }
        setContent {
            SlothosTheme {
                // A surface container using the 'background' color from the theme
                SlothosApp(viewModelFactory = viewModelFactory)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlothosTheme {
        //SlothosApp(viewModelFactory = viewModelFactory)
    }
}