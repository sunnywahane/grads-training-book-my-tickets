package com.demo.book.api

import com.demo.book.movie.service.ScreenService
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus

class ScreenApiTest : StringSpec(){

    init{
        "should return ok for get method"{
            val mockScreenService = mockk<ScreenService>(relaxed = true)
            val screenApi = ScreenApi(mockScreenService);
            val resultStatus = screenApi.allScreens().status;
            resultStatus shouldBe HttpStatus.OK
        }

        "should return ok for post method"{
            val mockScreenService = mockk<ScreenService>(relaxed = true)
            val screenApi = ScreenApi(mockScreenService);
            val resultStatus = screenApi.addScreen().status;
            resultStatus shouldBe HttpStatus.OK
        }
    }

}
