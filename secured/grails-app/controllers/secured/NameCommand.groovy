package secured

import grails.validation.Validateable

class NameCommand implements Validateable {
    String name
    String description

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: false;
    }
}