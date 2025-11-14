package ai.voya.androidinterview.repository

import ai.voya.androidinterview.model.CharacterResults

interface CharacterRepository {

    suspend fun getCharacters(): CharacterResults
}