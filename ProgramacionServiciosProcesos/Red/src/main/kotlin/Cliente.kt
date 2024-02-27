import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

fun main() {
    try {
        val puerto = 6000
        val socket = Socket("localhost", puerto)

        val input = BufferedReader(InputStreamReader(socket.inputStream))
        val output = socket.getOutputStream()

        output.write("notepad".toByteArray())
        output.write("\n".toByteArray())
        output.flush()

    }catch (e: Exception){

        e.printStackTrace()
    }
}