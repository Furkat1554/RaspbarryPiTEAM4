package secured

import grails.validation.Validateable
import java.time.LocalDateTime

class NameCommand implements Validateable {
    String name
    String description
    Date postedDate = new Date()
    Date endDate

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: false
        endDate min: new Date()
    }
}