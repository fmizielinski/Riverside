package pl.fmizielinski.riverside

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import pl.fmizielinski.riverside.di.ViewModelModule
import pl.fmizielinski.riverside.domain.di.DI

class RiversideApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RiversideApplication)
            modules(DI.modules + ViewModelModule().module)
        }
    }
}
