package pl.fmizielinski.riverside.domain.di

import org.koin.ksp.generated.module
import pl.fmizielinski.riverside.data.di.NetworkModule

object DI {

    val modules = listOf(
        UseCaseModule().module,
        RepositoryModule().module,
        NetworkModule().module,
    )
}
