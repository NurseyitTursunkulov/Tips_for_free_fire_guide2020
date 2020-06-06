package com.example.tipsforfreefireguide2020.bookList

import java.util.*

data class Book(
    val title: String = "dfrfa",
    val id: String = UUID.randomUUID().toString(),
    var imageId: Int,
    var body: String = "",
    var listOfContentPerPage: List<String> = mutableListOf<String>()
)
