package ai.voya.androidinterview.repository

import ai.voya.androidinterview.model.CharacterResults
import ai.voya.androidinterview.model.RickAndMortyApi

class CharacterRepositoryImpl(
    val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(): CharacterResults = api.getAllCharacters()
}