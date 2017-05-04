package secured

import grails.transaction.Transactional

class AnnouncementGormService {

    @Transactional(readOnly = true)
    List list(Map params) {
        [Announcement.list(params), Announcement.count()]
    }

    // tag::updateMediaFileUrl[]
    @Transactional
    Announcement updateMediaFileUrl(Long announcementId, String mediaFileUrl) {
        Announcement announcement = Announcement.get(announcementId)
        if ( !announcement ) {
            return null
        }
        announcement.mediaFileUrl = mediaFileUrl
        announcement.save()
    }
    // end::updateMediaFileUrl[]

    @Transactional
    Announcement save(NameCommand cmd) {
        Announcement announcement = new Announcement()
        announcement.properties = cmd.properties
        announcement.save()
    }

    @Transactional
    void deleteById(Long announcementId) {
        Announcement announcement = Announcement.get(announcementId)
        announcement?.delete()
    }
}
