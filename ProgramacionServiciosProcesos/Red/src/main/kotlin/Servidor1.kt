import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.nio.Buffer

class Servidor1 {
    fun arracncar(){
        try {
            val server = ServerSocket(6000)
            val cliente = server.accept()
            val lectura = BufferedReader(InputStreamReader(cliente.inputStream))
            val escritura = PrintWriter(cliente.outputStream,true)

            var mensaje :String?

            do {
                mensaje = lectura.readLine()
                println(mensaje)
                escritura.println("llego")
            }while (mensaje != "fin")
            lectura.close()
            escritura.close()
            cliente.close()
            server.close()
        }catch (e :Exception){
            e.printStackTrace()
        }
    }
}
fun main() {
    val server = Servidor1()
    server.arracncar()
}