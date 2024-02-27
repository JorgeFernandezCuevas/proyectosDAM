fun cifrar(palabra:String,desplazamiento :Int):String{
    return palabra.map { (it+desplazamiento).toChar() }.joinToString("")
}
fun descifrar(palabraCifrada:String,desplazamiento :Int):String{
    return palabraCifrada.map { (it-desplazamiento).toChar() }.joinToString("")

}


fun main() {
    val palabra = "hola"
    val palabraCifrar = cifrar(palabra,3)
    println(palabraCifrar)
    val palabraDescifrada = descifrar(palabraCifrar,3)
    println(palabraDescifrada)
}