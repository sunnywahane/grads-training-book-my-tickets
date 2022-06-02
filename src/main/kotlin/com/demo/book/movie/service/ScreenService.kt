package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.entity.Screen
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.repository.ScreenRepository
import com.demo.book.movie.request.MovieRequest
import com.demo.book.movie.request.ScreenRequest
import javax.inject.Inject

class ScreenService(@Inject val screenRepository: ScreenRepository) {

    fun save(screenRequest: ScreenRequest): Screen {
        return screenRepository.save(screenRequest)
    }

    fun allMovies(): List<Screen> {
        return listOf(Screen(1, "Screen 1", 100))
    }
}
