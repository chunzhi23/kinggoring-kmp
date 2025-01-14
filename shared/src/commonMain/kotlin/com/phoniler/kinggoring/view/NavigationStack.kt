package com.phoniler.kinggoring.view

import androidx.compose.runtime.mutableStateListOf

class NavigationStack<T>(
    vararg initial: T,
) {
    val stack = mutableStateListOf(*initial)

    fun push(t: T) {
        stack.add(t)
    }

    fun back() {
        if (stack.size > 1) {
            stack.removeLast()
        }
    }

    fun reset() {
        stack.removeRange(1, stack.size)
    }

    fun lastWithIndex() = stack.withIndex().last()
}
