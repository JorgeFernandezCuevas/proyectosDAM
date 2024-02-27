import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.FileInputStream

fun main(){

    val clienteftp = FTPClient()
    try{
        clienteftp.connect("localhost", 21)
        clienteftp.login("dam2", "admin")
        clienteftp.enterLocalPassiveMode()
        clienteftp.setFileType(FTP.BINARY_FILE_TYPE)

        val inputStream = FileInputStream("C:\\Users\\erica\\OneDrive\\Documentos\\GitHub\\EricAlonso_PRO\\DAM2\\PSP\\2ºTRIMESTRE\\Servicios\\archivo.docx")
        val exito = clienteftp.storeFile("/ingles/archivo.txt",inputStream)
        inputStream.close()

        if (exito){
            println("Archivo subido con exito")
        }else{
            println("Archivo fallido")
        }

        val ficheros = clienteftp.listFiles("/")
        for(fichero in ficheros){
            if(fichero.isFile){
                print("Fichero:  $fichero")
            }else{
                print("Directorio: $fichero")
            }
        }
    }catch (e : Exception){
        e.printStackTrace()
    }finally {
        clienteftp.logout()
        clienteftp.disconnect()
    }

}