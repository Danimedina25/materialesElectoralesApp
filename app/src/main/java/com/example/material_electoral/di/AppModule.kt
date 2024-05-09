package com.example.material_electoral.di


import com.example.material_electoral.caels.data.remote.service.CaelsApi
import com.example.material_electoral.login.data.remote.service.LoginApi
import com.example.material_electoral.materiales.data.remote.service.MaterialesApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.8.103:5000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginMaterialElectoralApi(): LoginApi{
        return getRetrofit().create()
    }

    @Provides
    @Singleton
    fun provideCaelsMaterialElectoralApi(): CaelsApi {
        return getRetrofit().create()
    }

    @Provides
    @Singleton
    fun provideMaterialesMaterialElectoralApi(): MaterialesApi {
        return getRetrofit().create()
    }



}