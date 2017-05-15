package secured

import grails.validation.Validateable

class NameCommand implements Validateable {
    String title
    String description
    String author
    Date postedDate = new Date()
    Date endDate

    static constraints = {
        title nullable: false, blank: false
        description nullable: true, blank: false
        author nullable: true
        endDate()
    }
}