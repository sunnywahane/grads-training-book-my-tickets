package com.demo.book.movie.service
import com.demo.book.movie.entity.Movie
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

const val MOVIE_DURATION_LOWER_LIMIT_IN_SECONDS = 5
const val MOVIE_DURATION_UPPER_LIMIT_IN_SECONDS = 360
@Singleton
class MovieService(@Inject val movieRepository: MovieRepository) {
    fun save(movieRequest: MovieRequest): Movie {
        val durationInSeconds= movieRequest.duration
        if ( durationInSeconds !in MOVIE_DURATION_LOWER_LIMIT_IN_SECONDS..MOVIE_DURATION_UPPER_LIMIT_IN_SECONDS)
            throw InvalidDurationException("")
        return movieRepository.save(movieRequest)
    }

    fun allMovies(): List<Movie> {
        return movieRepository.findAll()
    }

    fun findMovieById(id : Int): Movie{
        return movieRepository.findOne(id)
    }


}
