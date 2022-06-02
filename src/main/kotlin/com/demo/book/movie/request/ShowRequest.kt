package com.demo.book.movie.request

import java.math.BigDecimal

data class ShowRequest(
    val startTime: Long,
    val movieId: Int,
    val price: Double
)

