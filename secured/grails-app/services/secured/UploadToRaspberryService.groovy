package secured

import grails.core.GrailsApplication
import grails.transaction.Transactional
import org.apache.commons.net.ftp.FTPClient

@Transactional
class UploadToRaspberryService {

    GrailsApplication grailsApplication
    FTPClient ftpClient

    String upload(String fileName, InputStream inputStream, String IP) {
        String status
        ftpClient.with {
            connect '192.168.21.254'
            login 'alibek', '1'
            enterLocalPassiveMode()
            setFileType(BINARY_FILE_TYPE)
            changeWorkingDirectory '/home/alibek/Downloads'
            storeFile(fileName, inputStream)
            status = replyString
            disconnect()
        }
        return status
    }
}
