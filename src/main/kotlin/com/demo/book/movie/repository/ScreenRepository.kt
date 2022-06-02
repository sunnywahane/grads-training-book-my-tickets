package com.demo.book.movie.repository

import javax.inject.Inject
import javax.sql.DataSource

class ScreenRepository(@Inject private val datasource: DataSource) {

}
