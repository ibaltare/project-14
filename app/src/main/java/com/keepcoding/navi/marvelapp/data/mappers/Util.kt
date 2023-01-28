package com.keepcoding.navi.marvelapp.data.mappers

import java.math.BigInteger
import java.security.MessageDigest

fun String.toMD5Hash(): String {
    val md5 = MessageDigest.getInstance("MD5")
    return BigInteger(1, md5.digest(this.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}