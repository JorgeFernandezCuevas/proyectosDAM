import okhttp3.OkHttpClient
import okhttp3.Request
import java.awt.Desktop
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

fun main() {
        val cliente = OkHttpClient()
        val url = "https://www.jesusninoc.com/"
        val peticion = Request.Builder().url(url).build()
        val respuesta = cliente.newCall(peticion).execute()
        val respuestaTotal = respuesta.body?.string()
        println(respuestaTotal)
}