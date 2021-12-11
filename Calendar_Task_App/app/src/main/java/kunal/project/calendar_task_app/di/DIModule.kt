package kunal.project.calendar_task_app.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.plugins.RxJavaPlugins
import kunal.project.calendar_task_app.data.local.TaskDAO
import kunal.project.calendar_task_app.data.local.TaskDatabase
import kunal.project.calendar_task_app.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DIModule {


    @Singleton
    @Provides
    fun provideAPIService(): ApiService {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = Retrofit.Builder()
            .baseUrl("http://13.232.92.136:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
            .build()
        return builder.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTaskDB(@ApplicationContext context: Context): TaskDatabase {
        val builder = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideTaskDAO(taskDatabase: TaskDatabase) : TaskDAO {
        return taskDatabase.getDAO()
    }
}