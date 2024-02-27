import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

class Cliente1 {
    fun arrancarCliente(){
        try {
            val clienteSock= Socket("localhost",6000)
            val lectura = BufferedReader(InputStreamReader(clienteSock.inputStream))
            val escritura = PrintWriter(clienteSock.outputStream,true)

            escritura.println("Hola servidor")
            var leer = lectura.readLine()
            println(leer)
            lectura.close()
            escritura.close()
            clienteSock.close()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}

fun main() {
    val cliente = Cliente1()
    cliente.arrancarCliente()
}