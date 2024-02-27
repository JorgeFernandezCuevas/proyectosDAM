import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.FileOutputStream
import java.security.MessageDigest

fun main() {
    val ftpCliente = FTPClient()
    try {
        ftpCliente.connect("localhost",21)
        ftpCliente.login("dam2","dam2")
        ftpCliente.enterLocalPassiveMode()
        ftpCliente.setFileType(FTP.BINARY_FILE_TYPE)
        ftpCliente.changeWorkingDirectory("curso")

        val output= FileOutputStream("archivo.txt")
        ftpCliente.retrieveFile("latetica.txt",output)
        output.close()


    }catch (e:Exception){
        e.printStackTrace()
    }
}
fun calcular(file : String) : String{
    val buffer = ByteArray(8192)
    val md = MessageDigest.getInstance("SHA-512")
    val input = file.byteInputStream()
    var leer :Int
    do{
        leer = input.read(buffer)
        if(leer > 0){
            md.update(buffer,0,leer)
        }
    }while(leer != -1)
    input.close()
    val hash = md.digest()
    val string = StringBuilder()
    for(b in hash){
        string.append(String.format("%02x",b))
    }
    return string.toString()
}