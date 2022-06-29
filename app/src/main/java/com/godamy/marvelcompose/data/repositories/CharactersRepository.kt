package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.data.entities.Url
import com.godamy.marvelcompose.data.network.ApiClient
import com.godamy.marvelcompose.data.network.entities.ApiCharacter
import com.godamy.marvelcompose.data.network.entities.asString

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.toDomainModel() }
    }

    suspend fun find(characterId: Int): Result<Character> = super.find(
        id = characterId,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(characterId)
                .data
                .results
                .first()
                .toDomainModel()
        }
    )

    private fun ApiCharacter.toDomainModel(): Character =
        Character(
            id = id,
            name,
            description = description,
            thumbnail = thumbnail.asString(),
            listOf(
                comics.toDomainModel(ReferenceList.Type.COMIC),
                stories.toDomainModel(ReferenceList.Type.STORY)
            ),
            urls = urls.map { Url(it.type, it.url) }
        )
}
