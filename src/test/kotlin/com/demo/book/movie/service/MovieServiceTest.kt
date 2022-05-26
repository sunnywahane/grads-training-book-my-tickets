package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.exp

class MovieServiceTest : StringSpec() {
    private val mockMovieRepository = mockk<MovieRepository>(relaxed = true)

    override fun afterEach(testCase: TestCase, result: TestResult) {
        super.afterEach(testCase, result)
        clearAllMocks()
    }

    init {

        "Adding a movie with length 5 minutes" {
            val movieRequest = MovieRequest("test", 1653586200000, 1653586500000)
            val expected = Movie(1, "test", LocalDateTime.ofEpochSecond(1653579000000,0, ZoneOffset.UTC), LocalDateTime.ofEpochSecond(1653586200000, 0, ZoneOffset.UTC))
            every { MovieService(mockMovieRepository).save(movieRequest) } returns expected

            val actual = MovieService(mockMovieRepository).save(movieRequest)
            actual shouldBe expected
        }

        "Adding a movie with length 6 hours" {
            val movieRequest = MovieRequest("test", 1653570000000, 1653591600000)
            val expected = Movie(1, "test",
                LocalDateTime.ofEpochSecond(1653570000000,0, ZoneOffset.UTC),
                LocalDateTime.ofEpochSecond(1653591600000, 0, ZoneOffset.UTC))
            every { MovieService(mockMovieRepository).save(movieRequest) } returns expected

            val actual = MovieService(mockMovieRepository).save(movieRequest)
            actual shouldBe expected
        }

        "Adding a movie with length less than 4 minutes should throw an error"{
            val movieRequest = MovieRequest("test", 1653586200000, 1653586380000)
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(movieRequest)
            }
        }

        "Adding a movie with length less than 0 minutes should throw an error"{
            val movieRequest = MovieRequest("test", 1653586200000, 1653586200000)
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(movieRequest)
            }
        }

        "Adding a movie with length 6 hours and 1 minute should throw error" {
            val movieRequest = MovieRequest("test", 1653550200000, 1653571860000)
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(movieRequest)
            }
        }

    }

}
