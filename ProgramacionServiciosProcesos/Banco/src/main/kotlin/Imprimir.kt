import java.io.File

fun main() {
    val fichero = "output.txt"
    val proceso= ProcessBuilder("cmd", "/c","C:\\Users\\Jorge\\.jdks\\openjdk-21\\bin\\java.exe \"-javaagent:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2023.2.3\\lib\\idea_rt.jar=53439:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2023.2.3\\bin\" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\\Users\\Jorge\\Documents\\GitHub\\JorgeFernandez_PRO\\codigos22-24\\DAM2\\PSP\\Banco\\out\\production\\Banco;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-jdk8\\1.9.0\\kotlin-stdlib-jdk8-1.9.0.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\1.9.0\\kotlin-stdlib-1.9.0.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-common\\1.9.0\\kotlin-stdlib-common-1.9.0.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-jdk7\\1.9.0\\kotlin-stdlib-jdk7-1.9.0.jar;C:\\Users\\Jorge\\.m2\\repository\\com\\squareup\\okhttp3\\okhttp\\4.11.0\\okhttp-4.11.0.jar;C:\\Users\\Jorge\\.m2\\repository\\com\\squareup\\okio\\okio\\3.2.0\\okio-3.2.0.jar;C:\\Users\\Jorge\\.m2\\repository\\com\\squareup\\okio\\okio-jvm\\3.2.0\\okio-jvm-3.2.0.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\1.6.20\\kotlin-stdlib-1.6.20.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-common\\1.6.20\\kotlin-stdlib-common-1.6.20.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-jdk8\\1.6.20\\kotlin-stdlib-jdk8-1.6.20.jar;C:\\Users\\Jorge\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib-jdk7\\1.6.20\\kotlin-stdlib-jdk7-1.6.20.jar EntregaKt")
    proceso.redirectOutput(ProcessBuilder.Redirect.to(File(fichero)))
    proceso.start()
}