package com.demo.book.movie.repository

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.entity.Screen
import com.demo.book.movie.request.MovieRequest
import com.demo.book.movie.request.ScreenRequest
import movie.*
import norm.query
import javax.inject.Inject
import javax.sql.DataSource

class ScreenRepository(@Inject private val datasource: DataSource) {

    fun save(screenToSave: ScreenRequest): Screen = datasource.connection.use { connection ->
        SaveScreenQuery().query(
            connection,
            SaveScreenParams(
                screenToSave.title,
                screenToSave.capacity,
            )
        )
    }.map {
        Screen(
            it.id,
            it.title,
            it.capacity
        )
    }.first()
}
