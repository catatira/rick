package ai.voya.androidinterview.core.di

import ai.voya.androidinterview.model.RickAndMortyApi
import ai.voya.androidinterview.repository.CharacterRepository
import ai.voya.androidinterview.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): CharacterRepository =
        CharacterRepositoryImpl(provideApi())

    @Provides
    @Singleton
    fun provideApi(): RickAndMortyApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)
}