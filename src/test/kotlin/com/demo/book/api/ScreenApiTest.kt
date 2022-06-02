package com.demo.book.api

import com.demo.book.BaseIntegrationSpec
import com.demo.book.movie.request.ScreenRequest
import com.demo.book.utils.post
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class ScreenApiTest : BaseIntegrationSpec() {

    init {
        "should save screen" {
            // Given
            val screenReq = newScreenRequest()

            // When
            val response = createNewScreen(screenReq)

            // Then
            response.status shouldBe HttpStatus.OK
            response.body.get() shouldBe 1
        }
    }

    private fun createNewScreen(screenReq: ScreenRequest): HttpResponse<Any> {
        return httpClient.post(
            url = "/screens",
            body = jsonMapper.writeValueAsString(screenReq)
        )
    }

    private fun newScreenRequest(): ScreenRequest {
        return ScreenRequest(
            "Avengers",
            50
        )
    }
}
