package pl.fmizielinski.riverside.data

import org.koin.core.annotation.Factory

// FIXME: Use values from gradle.properties
@Factory
class Config {

    val apiKey = "37f37f14"
    val host = "https://www.omdbapi.com"
}
