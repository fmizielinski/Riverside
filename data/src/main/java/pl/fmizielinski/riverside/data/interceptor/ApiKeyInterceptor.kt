package pl.fmizielinski.riverside.data.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.annotation.Factory
import pl.fmizielinski.riverside.data.Config

@Factory
class ApiKeyInterceptor(
    private val config: Config,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalUrl = originalRequest.url()

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", config.apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
