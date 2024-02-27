import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.security.KeyStore
import java.security.MessageDigest
import javax.net.ssl.*

class Servidor {
    fun main() {
        print("**** Wasap2 ****")
        println()
        println("Inicio de sesion")
        val puerto = 5556
        var mensaje = ""
        val keyStorePath = "C://Users//Jorge//Documents//GitHub//JorgeFernandez_PRO//codigos22-24//DAM2//PSP//Multicast//Multicast//AlmacenSrv"
        val keyStorePassword = "1234567"
        val keyPassword = "1234567"

        if (login()){
            println("Te has unido al chat")

            // Cargar el almacén de claves
            val keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream(keyStorePath), keyStorePassword.toCharArray())

            // Configurar el administrador de claves
            val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, keyPassword.toCharArray())

            // Configurar el administrador de confianza
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)


            /*clienteConectado = servidorSSL.accept() as SSLSocket
            flujoEntrada = DataInputStream(clienteConectado.getInputStream())

            flujoSalida = DataOutputStream(clienteConectado.getOutputStream())*/

            // Configurar el contexto SSL
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, null)

            // Crear el socket SSL del servidor
            val sslServerSocketFactory = sslContext.serverSocketFactory
            val servidorSSL = sslServerSocketFactory.createServerSocket(puerto) as SSLServerSocket
            val escritura = Thread {


                var clienteConectado: SSLSocket? = null
                var flujoEntrada: DataInputStream? = null
                var flujoSalida: DataOutputStream? = null

                clienteConectado = servidorSSL.accept() as SSLSocket
                //flujoEntrada = DataInputStream(clienteConectado.getInputStream())
                flujoSalida = DataOutputStream(clienteConectado.getOutputStream())
                while (true){

                    mensaje = readln()
                    flujoSalida.writeUTF(mensaje)

                }
                flujoEntrada?.close()
                flujoSalida?.close()
                clienteConectado?.close()
                servidorSSL.close()

            }
            val lectura= Thread{

                var clienteConectado2: SSLSocket? = null
                var flujoEntrada: DataInputStream? = null
                var flujoSalida: DataOutputStream? = null

                clienteConectado2 = servidorSSL.accept() as SSLSocket
                flujoEntrada = DataInputStream(clienteConectado2.getInputStream())
                while (true){

                    val mensajec = flujoEntrada.readUTF().toString()
                    if(!mensajec.equals(mensaje)){
                        println("   ${mensajec}")
                    }
                }
                flujoEntrada?.close()
                flujoSalida?.close()
                clienteConectado2?.close()
                servidorSSL.close()
            }
            lectura.start()
            escritura.start()
        }
    }
}
fun login():Boolean{
    //pass = dd4019fc37f16b78bcf12b07990a56771f090e583194b48fe843dcb0ea8c5817d05c439506f6db8348baa2d9e82258225e93c54823f9f8792933e4b18e17a66d
    println("-Introduce la contraseña:")
    println()
    val pass = readln()
    val semilla = "1234###ad"
    val sha512 = MessageDigest.getInstance("SHA-512")
    val bytes = (pass+semilla).toByteArray()

    val hash = sha512.digest(bytes)
    if(hash.joinToString("") { "%02x".format(it) } =="dd4019fc37f16b78bcf12b07990a56771f090e583194b48fe843dcb0ea8c5817d05c439506f6db8348baa2d9e82258225e93c54823f9f8792933e4b18e17a66d"){
        print("*Contraseña correcta*")
        println()
        return true
    } else{
        print("hahaha")
        return false
    }
}

fun main(args: Array<String>) {
    // Iniciar servidor
    val servidor = Servidor()
    servidor.main()
}
