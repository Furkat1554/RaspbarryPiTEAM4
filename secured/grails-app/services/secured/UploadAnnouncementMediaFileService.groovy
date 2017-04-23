package secured

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import secured.Announcement

class UploadAnnouncementMediaFileService implements GrailsConfigurationAware {

    String cdnFolder
    String cdnRootUrl

    def announcementGormService

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('grails.guides.cdnFolder')
        cdnRootUrl = co.getRequiredProperty('grails.guides.cdnRootUrl')
    }

    Announcement uploadMediaFile(MediaFileCommand cmd) {
        String filename = cmd.mediaFile.originalFilename
        def folderPath = "${cdnFolder}/announcement/${cmd.id}" as String
        def folder = new File(folderPath)
        if ( !folder.exists() ) {
            folder.mkdirs()
        }
        def path = "${folderPath}/${filename}" as String
        cmd.mediaFile.transferTo(new File(path))

        String mediaFileUrl = "${cdnRootUrl}//announcement/${cmd.id}/${filename}"

        def poi = announcementGormService.updateMediaFileUrl(cmd.id, mediaFileUrl)

        if ( !poi || poi.hasErrors() ) {
            def f = new File(path)
            f.delete()
        }
        poi
    }
}
