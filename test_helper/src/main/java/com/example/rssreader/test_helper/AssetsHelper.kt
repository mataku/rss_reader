package com.example.rssreader.test_helper

import java.io.File
import java.io.FileNotFoundException

object AssetsHelper {
    @JvmStatic
    @Throws(FileNotFoundException::class)
    fun getAssetFileStrings(name: String): String {
        val dir = "../test_helper"
        val file = File(dir, "src/test/assets/$name")
        if (file.exists()) {
            return file.readText(Charsets.UTF_8)
        }

        throw FileNotFoundException("No resource file: $name")
    }
}
