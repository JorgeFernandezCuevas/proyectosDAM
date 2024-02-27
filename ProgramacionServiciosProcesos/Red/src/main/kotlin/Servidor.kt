import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

fun main() {
    try {

        val puerto = 6000
        val servidor = ServerSocket(puerto)
        println("Servidor esperando en el puerto: $puerto")
        val cliente = servidor.accept()
        println("Primer cliente conectado")
        val input = BufferedReader(InputStreamReader(cliente.getInputStream()))
        val output = cliente.getOutputStream()

        val comando = input.readLine()

        val processo = ProcessBuilder(comando)
        val ejecucion = processo.start()
        println(ejecucion.waitFor())



    }catch (e: Exception){

        e.printStackTrace()
    }
}