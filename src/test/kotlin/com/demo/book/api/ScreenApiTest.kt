package com.demo.book.api

import com.demo.book.movie.service.ScreenService
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.kotest.matchers.shouldBe

class ScreenApiTest : StringSpec(){

    init{
        "should return ok for get method"{
            val mockScreenService = mockk<ScreenService>(relaxed = true)
            val screenApi = ScreenApi(mockScreenService);
            val resultStatus = screenApi.allScreens().status;
            resultStatus shouldBe "OK"
        }
    }

}
