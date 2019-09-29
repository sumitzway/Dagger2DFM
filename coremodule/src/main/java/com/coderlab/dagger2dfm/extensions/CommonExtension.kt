package com.coderlab.dagger2dfm.extensions

fun Any.getObjectKey(): String = Integer.toHexString(hashCode())