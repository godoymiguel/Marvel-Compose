package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.data.entities.Url
import com.godamy.marvelcompose.data.network.entities.ApiEvent
import com.godamy.marvelcompose.data.network.entities.asString
import com.godamy.marvelcompose.data.network.remote.EventsService
import javax.inject.Inject

class EventsRepository @Inject constructor(private val eventsService: EventsService) :
    Repository<Event>() {

    suspend fun get(): Result<List<Event>> =
        super.get {
            eventsService
                .getEvents(0, 100)
                .data
                .results
                .map { it.toDomainModel() }
        }

    suspend fun find(id: Int): Result<Event> =
        super.find(
            id = id,
            findActionRemote = {
                eventsService
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