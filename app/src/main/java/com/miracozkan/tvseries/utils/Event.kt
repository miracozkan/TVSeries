package com.miracozkan.tvseries.utils


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 01.10.2019 - 11:21          │
//└─────────────────────────────┘

open class Event<out T>(private val content: T) {

    var hasEnable = false
        private set

    fun getContentIfHandled(): T? {
        return if (hasEnable) {
            null
        } else {
            hasEnable = true
            content
        }
    }

    fun peekContent(): T = content

}