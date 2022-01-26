package com.piotrprus

expect val platform: String

class Greeting {
    fun greeting() = "Hello, $platform!"
}