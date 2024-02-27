import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    try{

        val conexion = DatagramSocket()
        val mensaje = "Hola Servidor UDP"
        val mensajeByte = mensaje.toByteArray()
        val ip = InetAddress.getByName("127.0.0.1")

        val paquete = DatagramPacket(mensajeByte,mensajeByte.size,ip,6000)
        conexion.send(paquete)


    }catch (e: Exception){
        e.printStackTrace()
    }
}