package com.demo.movie.api

import com.demo.movie.domain.MovieService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller
class MovieApi(@Inject val movieService: MovieService) {

    @Get("/movies")
    fun checkIfApprenticeExists(): HttpResponse<String> = HttpResponse.ok("Hello Movies!")

}
