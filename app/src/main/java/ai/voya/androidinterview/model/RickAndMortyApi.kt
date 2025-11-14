package ai.voya.androidinterview.model

import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(): CharacterResults
}