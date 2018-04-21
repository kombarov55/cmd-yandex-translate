package com.company

fun main(args: Array<String>) {
    println("Здравствуйте! Вводите текст для перевода")

    translateLoop@
    while (true) {
        val text = readLine()
        when (text) {
            null -> continue@translateLoop
            "exit" -> break@translateLoop
            "" -> continue@translateLoop
            else -> {
                val translation = translate(text)
                println(translation)
            }
        }
    }
}

private val domain = "https://translate.yandex.net"
private val key = "TOP_SECRET"
private val url = "$domain/api/v1.5/tr.json/translate?key=$key"

private fun translate(text: String?): List<String> {
    return khttp.post(
            url = url,
            data = mapOf("text" to text, "lang" to "ru")
    ).jsonObject.getJSONArray("text").map { it -> it.toString() }
}

