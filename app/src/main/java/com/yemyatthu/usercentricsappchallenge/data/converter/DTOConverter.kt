package com.yemyatthu.usercentricsappchallenge.data.converter

interface DTOConverter<SOURCE, DEST> {
    fun convert(source: SOURCE): DEST
}