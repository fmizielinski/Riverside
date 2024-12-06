package pl.fmizielinski.riverside.domain.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("pl.fmizielinski.riverside.domain.repository")
class RepositoryModule
