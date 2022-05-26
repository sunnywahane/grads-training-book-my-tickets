package com.demo.book.movie.service
import com.demo.book.movie.entity.Movie
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class MovieService(@Inject val movieRepository: MovieRepository) {
    fun save(movieRequest: MovieRequest): Movie {
        val seconds= (abs(movieRequest.startTime - movieRequest.endTime)/1000)
        if ( seconds < 300 || seconds > 21600) throw InvalidDurationException("Invalid duration")
        return movieRepository.save(movieRequest)
    }

    fun allMovies(): List<Movie> {
        return movieRepository.findAll()
    }
}
