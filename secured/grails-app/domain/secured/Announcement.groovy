package secured

class Announcement {

    String name
    String description
    String mediaFileUrl
    Date endDate


    static constraints = {
        name maxSize: 20, nullable: false
        description widget: 'textarea', maxSize: 120
        mediaFileUrl nullable:true
        endDate min: new Date()
    }

}
