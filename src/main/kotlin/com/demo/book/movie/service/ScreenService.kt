package com.demo.book.movie.service

import com.demo.book.movie.repository.MovieRepository
import javax.inject.Inject

class ScreenService(@Inject val screenRepository: MovieRepository) {

}
