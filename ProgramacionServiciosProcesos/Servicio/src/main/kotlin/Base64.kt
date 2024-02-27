import java.util.Base64

fun main() {
    val texto = "two niggas pissing in the same glass"

    val codificar = Base64.getEncoder().encodeToString(texto.toByteArray())
    println(codificar)

    val decodificar = String(Base64.getDecoder().decode(codificar))
    println(decodificar)
}