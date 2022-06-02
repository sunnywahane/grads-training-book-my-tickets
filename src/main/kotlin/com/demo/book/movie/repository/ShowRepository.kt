package com.demo.book.movie.repository

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.entity.Show
import com.demo.book.movie.request.BookRequest
import com.demo.book.movie.request.MovieRequest
import com.demo.book.movie.request.ShowRequest
import liquibase.pro.packaged.it
import movie.*
import norm.query
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class ShowRepository(@Inject private val datasource: DataSource) {

    fun save(showToSave: ShowRequest): Show = datasource.connection.use { connection ->
        SaveShowQuery().query(
            connection,
            SaveShowParams(
                Timestamp.from(Instant.ofEpochMilli(showToSave.startTime)),
                showToSave.movieId
            )
        )
    }.map {
        Show(
            it.id,
            it.startTime.toLocalDateTime(),
            it.movieId,
            it.seats
        )
    }.first()

    fun findAll(): List<Show> = datasource.connection.use { connection ->
        GetAllShowsQuery().query(
            connection,
            GetAllShowsParams()
        )
    }.map {
        Show(
            it.id,
            it.startTime.toLocalDateTime(),
            it.movieId,
            it.seats
        )
    }

    fun update(bookRequest: BookRequest): Boolean {
        return true

    }

}
