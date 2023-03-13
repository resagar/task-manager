package com.resagar.todolistxml.data.database

import android.content.Context
import androidx.room.Room
import com.resagar.todolistxml.data.database.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_tasks").build()

    @Singleton
    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}