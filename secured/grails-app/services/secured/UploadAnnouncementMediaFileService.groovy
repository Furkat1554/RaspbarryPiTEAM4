package secured

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import org.h2.store.FileStoreInputStream
import secured.Announcement

class UploadAnnouncementMediaFileService implements GrailsConfigurationAware {

    String cdnFolder
    String cdnRootUrl

    def announcementGormService
    def uploadToRaspberryService

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('grails.guides.cdnFolder')
        cdnRootUrl = co.getRequiredProperty('grails.guides.cdnRootUrl')
    }

    Announcement uploadMediaFile(MediaFileCommand cmd, String block) {
        String filename = cmd.mediaFile.originalFilename
        System.out.print(filename + "\n")
        def folderPath = "${cdnFolder}/announcement/${block}/${cmd.id}" as String
        def folder = new File(folderPath)
        if ( !folder.exists() ) {
            folder.mkdirs()
        }
        def path = "${folderPath}/${filename}" as String
        System.out.print(path + "\n")
        cmd.mediaFile.transferTo(new File(path))

        //share media file to raspberries
        File file = new File(path)
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file))

        def devices = getAllDevicesOfBlock(block)
        def ftpStatus
        for(String ip:devices){
            System.out.println(ip)
            //ftpStatus = uploadToRaspberryService.upload(filename,inputStream,ip)
        }
        if(ftpStatus != null){
            System.out.print(ftpStatus)
        }

        //add mediaFileUrl to database
        String mediaFileUrl = "${cdnRootUrl}//announcement/${block}/${cmd.id}/${filename}"
        def announ = announcementGormService.updateMediaFileUrl(cmd.id, mediaFileUrl)
        if ( !announ || announ.hasErrors() ) {
            def f = new File(path)
            f.delete()
        }
        announ
    }

    private String[] getAllDevicesOfBlock(String block) {
        def raspberries = Raspberry.findAllByBlock(block)
        def devices = new String[raspberries.size()]
        for (int i = 0; i < raspberries.size(); i++) {
            devices[i] = raspberries.get(i).getIp()
        }
        return devices
    }
}
