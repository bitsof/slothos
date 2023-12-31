package pro.selecto.slothos.di
/*
@Module
class ModuleTesting {
    @Provides
    @Singleton
    fun provideBlankThing(): Blank {
        return Blank()
    }
}

class Blank {

}

@Singleton
@Component(modules = [ModuleTesting::class])
interface AppComponent {
}

class CoffeeMaker {
    fun brew() = "Coffee brewed!"
}

@Module
class CoffeeModule {
    @Provides
    fun provideCoffeeMaker(): CoffeeMaker = CoffeeMaker()
}

@Component(modules = [CoffeeModule::class])
interface CoffeeComponent {
    fun inject(office: Office)
}

class Office  {
    @Inject
    lateinit var coffeeMaker: CoffeeMaker

    fun brewCoffee() = coffeeMaker.brew()
}
*/