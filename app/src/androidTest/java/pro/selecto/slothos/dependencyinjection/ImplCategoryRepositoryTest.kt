import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

/*class TestApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}*/
@RunWith(AndroidJUnit4::class)
class DaggerSetupTest {
/*
    lateinit var repository: ImplCategoryRepository

    @Before
    fun setup() {
        var component = DaggerAppComponent.builder().build()
        repository = component.repository()
    }

    @Test
    fun testDaggerSetupForRepository() {
        assertNotNull(repository)
        assertNotNull(repository.categoryDao)
        assertNotNull(repository.exerciseCategoryFKDao)
    } */
}