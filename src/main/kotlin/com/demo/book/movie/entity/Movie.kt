package com.demo.book.movie.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class Movie(
    val id: Int,
    val title: String,
    val duration: Int
)
