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

private val API = "https://translate.yandex.net"
private val key = "trnsl.1.1.20180417T211322Z.6b63d18701d4af12.647556fa4d6898394e1c69a15fc344a528c58de3"
private val url = "$API/api/v1.5/tr.json/translate?key=$key"

private fun translate(text: String?): List<String> {
    return khttp.post(
            url = url,
            data = mapOf("text" to text, "lang" to "ru")
    ).jsonObject.getJSONArray("text").map { it -> it.toString() }
}

