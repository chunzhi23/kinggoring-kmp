package com.phoniler.kinggoring

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform