package com.raj.navan.repo

data class Byline(
    val organization: String,
    val original: String,
    val person: List<Person>
)