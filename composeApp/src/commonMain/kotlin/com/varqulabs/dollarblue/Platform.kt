package com.varqulabs.dollarblue

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform