import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class PeticionOk(){
    private lateinit var valor:String
    private val lock = ReentrantLock()
    fun pedir(){
        lock.lock()
        try {
            val cliente = OkHttpClient()

            val url = "https://www.jesusninoc.com"

            val peticion = Request.Builder().url(url).build()

            val respuesta = cliente.newCall(peticion).execute()

            valor= respuesta.body?.string().toString()

        }finally {
            lock.unlock()
        }
    }
    fun imprimir(){
        lock.lock()
        try {
            println(valor)
        }finally {
            lock.unlock()
        }
    }
}
fun main() {
    val peticionOk = PeticionOk()
    val hilo1 = thread {
        for (i in 1 ..5){
            peticionOk.pedir()
            Thread.sleep(1000)
        }
    }
    val hilo2 = thread {
        for (i in 1 ..5){
            peticionOk.imprimir()
            Thread.sleep(1000)
        }
    }
    hilo1.join()
    hilo2.join()
}