package com.nutritiwallah.home.domain

enum class CustomDrawerState {
    opened,
    closed
}

fun CustomDrawerState.isOpened() : Boolean = this == CustomDrawerState.opened

fun CustomDrawerState.toggle(): CustomDrawerState {
    return if(this == CustomDrawerState.opened) CustomDrawerState.closed
    else CustomDrawerState.opened
}