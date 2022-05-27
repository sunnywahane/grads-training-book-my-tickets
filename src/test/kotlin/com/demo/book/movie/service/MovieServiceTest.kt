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
    private val mockMovieRepository = mockk<MovieRepository>()

    override fun afterEach(testCase: TestCase, result: TestResult) {
        super.afterEach(testCase, result)
        clearAllMocks()
    }

    init {

        "Adding a movie with length 5 minutes" {
            val movieRequest = getDummyMovieRequest(1653586200000, 1653586500000)
            val expected = getDummyMovie(1653586200000, 1653586500000)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val actual = MovieService(mockMovieRepository).save(movieRequest)
            actual shouldBe expected
        }

        "Adding a movie with length 6 hours" {
            val movieRequest = getDummyMovieRequest(1653570000000, 1653591600000)
            val expected = getDummyMovie(1653570000000,1653591600000)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val actual = MovieService(mockMovieRepository).save(movieRequest)
            actual shouldBe expected
        }

        "Adding a movie with length 3 hours" {
            val movieRequest = getDummyMovieRequest(1653570000000, 1653580800000)
            val expected = getDummyMovie(1653570000000, 1653580800000)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val actual = MovieService(mockMovieRepository).save(movieRequest)
            actual shouldBe expected
        }

        "Adding a movie with length less than 4 minutes should throw an error"{
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(
                    getDummyMovieRequest(1653586200000, 1653586380000)
                )
            }
        }

        "Adding a movie with length less than 0 minutes should throw an error"{
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(
                    getDummyMovieRequest(1653586200000, 1653586200000)
                )
            }
        }

        "Adding a movie with length 6 hours and 1 minute should throw error" {
            assertThrows<InvalidDurationException> {
                MovieService(mockMovieRepository).save(
                    getDummyMovieRequest(1653550200000, 1653571860000)
                )
            }
        }
    }

    private fun getDummyMovieRequest(startTime: Long, endTime: Long): MovieRequest{
        return MovieRequest("test", startTime, endTime)
    }

    private fun getDummyMovie(startTime: Long, endTime: Long): Movie{
        return Movie(1, "test",
            LocalDateTime.ofEpochSecond(startTime,0, ZoneOffset.UTC),
            LocalDateTime.ofEpochSecond(endTime, 0, ZoneOffset.UTC))
    }
}
