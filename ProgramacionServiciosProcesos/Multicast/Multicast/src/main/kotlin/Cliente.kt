import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.security.KeyStore
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.net.ssl.*

class Cliente {
    fun main() {
        val host = "192.168.137.1"
        val puerto = 5556
        var mensaje = ""
        val keyStorePath = "C://Users//Jorge//Documents//GitHub//JorgeFernandez_PRO//codigos22-24//DAM2//PSP//Multicast//Multicast//AlmacenSrv"
        val keyStorePassword = "1234567"
        val keyPassword = "1234567"



        // Creo flujo de salida al servidor


        val escritura = Thread {

            // Cargar el almacén de claves
            val keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream(keyStorePath), keyStorePassword.toCharArray())

            // Configurar el administrador de claves
            val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, keyPassword.toCharArray())

            // Configurar el administrador de confianza
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)

            // Configurar el contexto SSL
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, null)
            val sslSocketFactory = sslContext.socketFactory
            val cliente = sslSocketFactory.createSocket(host, puerto) as SSLSocket
            val flujoSalida = DataOutputStream(cliente.getOutputStream())
            while (true){
                mensaje = readln()
                flujoSalida.writeUTF(mensaje)
            }
            flujoSalida?.close()
            cliente.close()
        }
        val lectura= Thread{
            // Cargar el almacén de claves
            val keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream(keyStorePath), keyStorePassword.toCharArray())

            // Configurar el administrador de claves
            val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, keyPassword.toCharArray())

            // Configurar el administrador de confianza
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)

            // Configurar el contexto SSL
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, null)

            val sslSocketFactory = sslContext.socketFactory
            val cliente2 = sslSocketFactory.createSocket(host, puerto) as SSLSocket
            val flujoEntrada = DataInputStream(cliente2.getInputStream())
            while (true){

                val mensajec = flujoEntrada.readUTF().toString()
                if(!mensajec.equals(mensaje)){
                    println("   ${mensajec}")
                }
            }
            flujoEntrada.close()
            cliente2.close()
        }
        lectura.start()
        escritura.start()

    }
}

fun main(args: Array<String>) {
    // Iniciar cliente
    val cliente = Cliente()
    if (login2()){
        cliente.main()
    }
}

fun login2(): Boolean{
//pass == 4835e76f3a965e81ce56a6f64bac3b42
    println("Introduce la contraseña:")
    val text = readln()

    val secret = SecretKeySpec("1234".toByteArray(), "HmacMD5")

    val algoritmo = Mac.getInstance("HmacMD5")

    algoritmo.init(secret)

    val hash = algoritmo.doFinal(text.toByteArray())

    val resultado = StringBuilder()

    for (byte in hash){

        resultado.append(String.format("%02x", byte))

    }
    if(resultado.toString() == "4835e76f3a965e81ce56a6f64bac3b42"){
        println("Contraseña correcta")
        return true
    }else{
        println("Contraseña incorrecta")
        return false
    }
}
