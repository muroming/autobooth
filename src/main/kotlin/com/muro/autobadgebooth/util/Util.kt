package com.muro.autobadgebooth.util

fun <T> List<T>.takeExactly(count: Int) = take(count).takeIf { it.size == count }
        ?: throw IllegalArgumentException("Collections contains less than $count items")