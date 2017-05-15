package secured


class Announcement {

    String title
    String description
    String author
    Date postedDate = new Date()
    Date endDate
    String mediaFileUrl


    static constraints = {
        title maxSize: 20, nullable: false
        description widget: 'textarea', maxSize: 120
        author nullable: true
        postedDate()
        endDate()
        mediaFileUrl nullable:true
    }

}
