package com.example.material_electoral.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
  /*  @Singleton
    @Provides
    fun provideMaterialElectoralDB(@ApplicationContext context : Context) : PokeApiDatabase{
        return Room.databaseBuilder(context, PokeApiDatabase::class.java, "PokeApiPruebaDB").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }*/
}