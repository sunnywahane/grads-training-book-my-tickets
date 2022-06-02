package com.demo.book.movie.service

import com.demo.book.movie.entity.Screen
import com.demo.book.movie.repository.ScreenRepository
import com.demo.book.movie.request.ScreenRequest
import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk

class ScreenServiceTest: StringSpec() {
    init {
        "should able to call save method on ScreenService" {
            val mockScreenRepository = mockk<ScreenRepository>(relaxed = true)
            val screenService = ScreenService(mockScreenRepository);
            val result = screenService.save(screenRequest = ScreenRequest("Screen 1", 100))
            val excepted = Screen(1, "Screen 1", 100)
            assert(result == excepted)
        }
    }
}
