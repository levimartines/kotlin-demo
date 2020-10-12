package com.levimartines.kotlindemo.models

data class Account(
        val id: String? = null,
        val name: String,
        val document: String?,
        val balance: Long? = 0
)
