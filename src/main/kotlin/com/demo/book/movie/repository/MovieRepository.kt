package com.demo.book.movie.repository

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.entity.Show
import com.demo.book.movie.request.MovieRequest
import movie.*
import norm.query
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class MovieRepository(@Inject private val datasource: DataSource) {

    fun save(movieToSave: MovieRequest): Movie = datasource.connection.use { connection ->
        SaveMovieQuery().query(
            connection,
            SaveMovieParams(
                movieToSave.title,
                movieToSave.duration
            )
        )
    }.map {
        Movie(
            it.id,
            it.title,
            it.duration!!
        )
    }.first()

    fun findAll(): List<Movie> = datasource.connection.use { connection ->
        GetAllMoviesQuery().query(
            connection,
            GetAllMoviesParams()
        )
    }.map {
        Movie(
            it.id,
            it.title,
            it.duration!!
        )
    }

    fun findOne(id: Int): Movie = datasource.connection.use {
            connection ->  MovieByIdQuery().query(
        connection,
        MovieByIdParams(
            id
        )
    )
    }.map {
        Movie(
            it.id,
            it.title,
            it.duration!!
        )
    }.first()

}
