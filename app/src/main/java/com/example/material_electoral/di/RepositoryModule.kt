package com.example.material_electoral.di

import com.example.material_electoral.caels.data.remote.repository.CaelsRepositoryImpl
import com.example.material_electoral.caels.domain.repository.CaelsRepository
import com.example.material_electoral.login.data.remote.repository.LoginRepositoryImpl
import com.example.material_electoral.login.domain.repository.LoginRepository
import com.example.material_electoral.materiales.data.remote.repository.MaterialesRepositoryImpl
import com.example.material_electoral.materiales.domain.repository.MaterialesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginMaterialElectoralRepository(
        loginMaterialElectoralRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindCaelsMaterialElectoralRepository(
        calesMaterialElectoralRepositoryImpl: CaelsRepositoryImpl
    ): CaelsRepository

    @Binds
    @Singleton
    abstract fun bindMaterialesMaterialElectoralRepository(
        materialesMaterialElectoralRepositoryImpl: MaterialesRepositoryImpl
    ): MaterialesRepository

}