package secured

import grails.validation.Validateable

class NameCommand implements Validateable {
    String name
    String description
    Date endDate

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: false
        endDate min: new Date()
    }
}