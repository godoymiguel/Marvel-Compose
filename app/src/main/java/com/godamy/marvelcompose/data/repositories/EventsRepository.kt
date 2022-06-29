package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.data.entities.Url
import com.godamy.marvelcompose.data.network.ApiClient
import com.godamy.marvelcompose.data.network.entities.ApiEvent
import com.godamy.marvelcompose.data.network.entities.asString

object EventsRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> =
        super.get {
            ApiClient
                .eventsService
                .getEvents(0, 100)
                .data
                .results
                .map { it.toDomainModel() }
        }

    suspend fun find(id: Int): Result<Event> =
        super.find(
            id = id,
            findActionRemote = {
                ApiClient
                    .eventsService
                    .findEvent(id)
                    .data
                    .results
                    .first()
                    .toDomainModel()
            }
        )

    private fun ApiEvent.toDomainModel(): Event =
        Event(
            id = id,
            name,
            description = description,
            thumbnail = thumbnail.asString(),
            listOf(
                comics.toDomainModel(ReferenceList.Type.COMIC),
                characters.toDomainModel(ReferenceList.Type.CHARACTER)
            ),
            urls = urls.map { Url(it.type, it.url) }
        )
}