package com.miracozkan.tvseries.utils

import android.view.View


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 01.10.2019 - 11:40          │
//└─────────────────────────────┘

fun View.showProgress() {
    this.visibility = View.VISIBLE
}

fun View.hideProgress() {
    this.visibility = View.GONE
}

fun View.dissappearProgress() {
    this.visibility = View.INVISIBLE
}