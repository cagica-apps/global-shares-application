package pt.cagica.globalsharesapplication.api.retrofit

import androidx.annotation.NonNull
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import pt.cagica.globalsharesapplication.BuildConfig

object RetrofitHelper {

    @NonNull
    fun buildRetrofitClient(standardClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(standardClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @NonNull
    fun setupOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HEADERS).setLevel(BODY))
            .connectTimeout(60, TimeUnit.SECONDS).build()
    }
}
