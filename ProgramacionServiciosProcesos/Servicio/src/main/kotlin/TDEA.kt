import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

fun main() {
    val texto ="ai micuki"

    val secreta:SecretKey = generarClaveSecreta()
    println(secreta)

    val cifrar = cifrar(texto,secreta)
    println(cifrar)

    val descifrar = descifrar(cifrar,secreta)
    println(descifrar)
}

fun generarClaveSecreta(): SecretKey {
    val generador = KeyGenerator.getInstance("DESede")
    generador.init(168)
    return generador.generateKey()
}
fun cifrar(texto:String,secreta:SecretKey):String{
    val cifrado = Cipher.getInstance("DESede")
    cifrado.init(Cipher.ENCRYPT_MODE,secreta)
    val textoCifrado = cifrado.doFinal(texto.toByteArray())
    return Base64.getEncoder().encodeToString(textoCifrado)

}
fun descifrar(cifrar:String,secreta:SecretKey):String{
    val cifrado = Cipher.getInstance("DESede")
    cifrado.init(Cipher.DECRYPT_MODE,secreta)
    val texto = cifrado.doFinal(Base64.getDecoder().decode(cifrar))
    return String(texto)
}
