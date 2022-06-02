package com.demo.book.api

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.service.MovieService
import com.demo.book.movie.service.ScreenService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/screens")
class ScreenApi(@Inject val screenService: ScreenService) {

    @Get
    fun allScreens(): HttpResponse<String> {
        return HttpResponse.ok("OK")
    }

    @Post
    fun addScreen(): HttpResponse<String> {
        return HttpResponse.ok("OK")
    }
}
