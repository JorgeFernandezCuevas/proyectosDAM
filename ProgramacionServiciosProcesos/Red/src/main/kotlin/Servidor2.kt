import java.awt.Robot
import java.awt.event.MouseEvent
import java.net.DatagramPacket
import java.net.DatagramSocket
fun main() {
    val server = DatagramSocket(2020)
    val buffer=ByteArray(1024)
    val r:Robot = Robot()
    while (true){
        val paquete = DatagramPacket(buffer,buffer.size)
        server.receive(paquete)
        val recibido = String(paquete.data,0,paquete.length)
        val x = recibido.split(" ")[0].toInt()
        val y = recibido.split(" ")[1].toInt()
        r.mouseMove(x,y)

    }


}