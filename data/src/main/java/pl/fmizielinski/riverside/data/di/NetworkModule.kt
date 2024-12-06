package pl.fmizielinski.riverside.data.di

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import pl.fmizielinski.riverside.data.Config
import pl.fmizielinski.riverside.data.interceptor.ApiKeyInterceptor
import pl.fmizielinski.riverside.data.service.OmdbService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@ComponentScan("pl.fmizielinski.riverside.data")
class NetworkModule {

    @Single
    fun retrofit(
        okHttpClient: OkHttpClient,
        config: Config,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(config.host)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @Factory
    fun okHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Single
    fun omdbService(retrofit: Retrofit): OmdbService = retrofit.create(OmdbService::class.java)

    @Single
    fun json(): Json = Json {
        ignoreUnknownKeys = true
    }
}
