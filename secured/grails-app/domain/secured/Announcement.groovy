package secured

import java.time.LocalDateTime

class Announcement {

    String name
    String description
    String mediaFileUrl
    Date postedDate = new Date()
    Date endDate


    static constraints = {
        name maxSize: 20, nullable: false
        description widget: 'textarea', maxSize: 120
        mediaFileUrl nullable:true
        postedDate()
        endDate min: new Date()
    }

}
