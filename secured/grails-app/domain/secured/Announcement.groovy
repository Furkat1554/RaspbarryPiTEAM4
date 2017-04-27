package secured

class Announcement {

    String name
    String description
    String mediaFileUrl
    Date startDate
    Date endDate

    static constraints = {
        name maxSize: 20
        description widget: 'textarea'

        endDate(validator: { val, obj ->
            val?.after(obj.startDate)
        })
        mediaFileUrl nullable:true


    }
}
