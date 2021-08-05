package com.demo.book.api

import com.demo.book.movie.entity.Movie
import com.demo.book.request.MovieRequest
import com.demo.book.utils.get
import com.demo.book.utils.post
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import norm.executeCommand
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.sql.DataSource

@MicronautTest
class MovieApiTest(
    @Client("/api") private val httpClient: HttpClient,
    @Inject private val dataSource: DataSource
) : StringSpec() {

    private val jsonMapper: ObjectMapper = jacksonObjectMapper().also {
        it.enable(SerializationFeature.INDENT_OUTPUT)
        it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        clearData()
    }

    fun clearData() {
        dataSource.connection.use { connection ->
            connection.executeCommand("TRUNCATE movies RESTART IDENTITY CASCADE;")
        }
    }

    init {
        "should get all saved movies" {
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            createNewMovie(newMovieRequest(
                referenceDate.toInstant().toEpochMilli(),
                referenceDate.plusHours(2).toInstant().toEpochMilli()
            ))

            val response = httpClient.get<List<Movie>>("/movies")

            response.status shouldBe HttpStatus.OK
            val savedMovies = response.body.get()
            savedMovies.size shouldBe 1
            jsonString(savedMovies[0]) shouldBe """
                |{
                |  "id" : 1,
                |  "title" : "Avengers",
                |  "startTime" : "2021-06-01 09:15:00.000",
                |  "endTime" : "2021-06-01 11:15:00.000"
                |}
            """.trimMargin().trimIndent()
        }
    }

    private fun jsonString(movie: Any?) = jsonMapper.writeValueAsString(movie)

    private fun createNewMovie(avengersMovie: MovieRequest) {
        httpClient.post(
            url = "/movies",
            body = jsonMapper.writeValueAsString(avengersMovie)
        )
    }

    private fun newMovieRequest(startTime: Long, endTime: Long): MovieRequest {
        val avengersMovie = MovieRequest(
            "Avengers",
            startTime,
            endTime
        )
        return avengersMovie
    }
}
