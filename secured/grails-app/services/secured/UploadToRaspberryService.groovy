package secured

import grails.core.GrailsApplication
import grails.transaction.Transactional
import org.apache.commons.net.ftp.FTPClient

@Transactional
class UploadToRaspberryService {

    GrailsApplication grailsApplication
    FTPClient ftpClient

    String upload(String fileName, InputStream inputStream) {
        String status
        ftpClient.with {
            connect grailsApplication.config.getProperty('ftp.host')
            login grailsApplication.config.getProperty('ftp.username'), grailsApplication.config.getProperty('ftp.password')
            enterLocalPassiveMode()
            setFileType(BINARY_FILE_TYPE)
            changeWorkingDirectory grailsApplication.config.getProperty('ftp.uploadDir')
            storeFile(fileName, inputStream)
            status = replyString
            disconnect()
        }
        return status
    }
}
