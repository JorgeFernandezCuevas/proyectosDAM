import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class Cuenta(var saldo :Int){
    private val lock = ReentrantLock()
    fun depositar(cantidad: Int){
        lock.lock()
        try {
            saldo+=cantidad
            println("He metido dinero la cuenta ahora tienes $saldo")
        }finally {
            lock.unlock()
        }
    }
    fun retirar(cantidad: Int){
        lock.lock()
        try {
            if (saldo>=cantidad){
                saldo-=cantidad
                println("Has retirado dinero $saldo")
            }else{
                println("No tienes dinero suficiente")
            }
        }finally {
            lock.unlock()

        }
    }
}

fun main() {
    val cuenta = Cuenta(1000)
    val hilo1 = thread {
        for (i in 1 ..5){
            cuenta.depositar(100)
            Thread.sleep(1000)
        }
    }
    val hilo2 = thread {
        for (i in 1 ..5){
            cuenta.retirar(50)
            Thread.sleep(1000)
        }
    }
    hilo1.join()
    hilo2.join()
}

