package com.demo.movie.api

import com.demo.movie.utils.get
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import javax.inject.Inject
import javax.sql.DataSource

@MicronautTest
class MovieApiTest(
    @Client("/api") private val httpClient: HttpClient,
    @Inject private val dataSource: DataSource
) : StringSpec() {

    init {
        "should get all saved movies" {
            val response = httpClient.get<HttpResponse<String>>(
                "/movies"
            )

            response.status shouldBe HttpStatus.OK
        }
    }
}
