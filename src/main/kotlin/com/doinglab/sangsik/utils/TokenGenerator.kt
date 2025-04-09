package com.doinglab.sangsik.utils

import java.util.*

object TokenGenerator {
    private val randomNumber = (0..9)

    @Synchronized
    fun generateNewToken(): String = UUID.randomUUID().toString()
        .replace("-".toRegex(), "")

    fun getRandomNumber(length: Int = 4): String {
        val rtn: ArrayList<Int> = arrayListOf()

        for(i: Int in 1..length) rtn.add(randomNumber.random())

        return rtn.joinToString("")
    }
}
