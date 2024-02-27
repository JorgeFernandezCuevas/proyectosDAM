import java.util.concurrent.Executors

fun main() {
    val numHilos = 5
    val ejecuciones=Executors.newFixedThreadPool(numHilos)
    val tareas = listOf(Tarea("Tarea1"), Tarea("Tarea2"),Tarea("Tarea3"),Tarea("Tarea4"),Tarea("Tarea5"))
    for (tarea in tareas){
        ejecuciones.execute(tarea)
    }
    ejecuciones.shutdown()
}

class Tarea(private val nombre:String):Runnable{
    override fun run() {
        println("Empieza el hilo $nombre")
        for (i in 1..5){
            println("Pasito $i")
            Thread.sleep(1000)
        }
    }

}