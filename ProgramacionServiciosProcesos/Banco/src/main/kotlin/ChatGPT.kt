import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val apiKey = "sk-code"
    val url = URL("https://api.openai.com/v1/models")

    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Authorization", "Bearer $apiKey")

    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
        val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
        val response = StringBuilder()
        var inputLine: String?

        while (inputStream.readLine().also { inputLine = it } != null) {
            response.append(inputLine)
        }

        inputStream.close()

        val jsonResponse = response.toString()
        println("Respuesta: $jsonResponse")
    } else {
        println("Error al realizar la solicitud: $responseCode")
    }

    connection.disconnect()
}