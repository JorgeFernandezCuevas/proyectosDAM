import java.util.LinkedList
import java.util.Queue
import kotlin.concurrent.thread

fun main() {
    val queue :Queue<Int> = LinkedList()

    val productor = Thread{
        for (i in 1..10){
            synchronized(queue){
                queue.add(i)
                println("Hemos añadido un elmento")
                Thread.sleep(1000)
            }
        }
    }
    val consumidor = Thread{
        for (i in 1..10){
            synchronized(queue){
                while (queue.isEmpty()){

                }
                val elemento = queue.poll()
                println("$elemento")
            }
        }
    }
    productor.start()
    consumidor.start()
    productor.join()
    consumidor.join()

}