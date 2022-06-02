package com.demo.book.movie.service

import com.demo.book.movie.repository.ScreenRepository
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk

class ScreenServiceTest: StringSpec() {
    init {
        "should able to call save method on ScreenService" {
            val mockScreenRepository = mockk<ScreenRepository>(relaxed = true)
            val screenService = ScreenService(mockScreenRepository);
            val result = screenService.save(screenRequest = ScreenRequest(name = "Screen 1", capacity = 100))
            val excepted = ScreenResponse(id = 1, name = "Screen 1", capacity = 100)
            assert(result == excepted)
        }
    }
}
