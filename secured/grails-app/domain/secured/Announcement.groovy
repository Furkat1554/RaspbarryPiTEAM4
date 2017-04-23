package secured

class Announcement {

    String name
    String description
    String mediaFileUrl

    static constraints = {
        mediaFileUrl nullable:true
    }
}
