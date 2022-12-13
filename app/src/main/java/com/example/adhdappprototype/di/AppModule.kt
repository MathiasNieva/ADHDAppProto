package com.example.adhdappprototype.di

import android.app.Application
import androidx.room.Room
import com.example.adhdappprototype.data.ComprehensiveTodoRepImpl
import com.example.adhdappprototype.data.Database
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.daily_planner.DailyPlanRepository
import com.example.adhdappprototype.data.daily_planner.DailyPlanRepositoryImpl
import com.example.adhdappprototype.data.daily_todo.DailyTodoRepository
import com.example.adhdappprototype.data.daily_todo.DailyTodoRepositoryImpl
import com.example.adhdappprototype.data.settings.SettingsRepository
import com.example.adhdappprototype.data.settings.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: Database): Repository {
        return ComprehensiveTodoRepImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideDailyTodoRepository(db: Database): DailyTodoRepository {
        return DailyTodoRepositoryImpl(db.dailyTodoDao)
    }

    @Provides
    @Singleton
    fun provideDailyPlanRepository(db: Database): DailyPlanRepository {
        return DailyPlanRepositoryImpl(db.dailyPlanDao)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(db: Database): SettingsRepository {
        return SettingsRepositoryImpl(db.settingsDao)
    }
}