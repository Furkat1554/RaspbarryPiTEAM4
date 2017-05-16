package secured

class Announcement {

    String title
    String description
    String author
    String block
    Date postedDate = new Date()
    Date endDate
    String mediaFileUrl

    static constraints = {
        title maxSize: 20, nullable: false
        description widget: 'textarea', maxSize: 120
        author nullable: true
        block nullable: false, inList: ["A","B","C","D"]
        postedDate()
        endDate()
        mediaFileUrl nullable:true
    }

}
