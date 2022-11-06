package com.marc0dev.compose1.models


data class Person(val info: Info?, var results: List<Result>)
data class Info(val count: Int?)
data class Result(val id: Int?, val name: String?, val status: String?, val image: String?)
