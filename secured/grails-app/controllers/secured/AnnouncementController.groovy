package secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class AnnouncementController {

    static allowedMethods = [save         : "POST",
                             update       : "PUT",
                             uploadMediaFile : 'POST',
                             delete       : "DELETE"]

    def uploadAnnouncementMediaFileService

    def AnnouncementGormService

    // tag::index[]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def (l, total) = AnnouncementGormService.list(params)
        respond l, model:[pointOfInterestCount: total]
    }
    // end::index[]

    // tag::editMediaFile[]
    @Transactional(readOnly = true)
    def editMediaFile(Announcement pointOfInterest) {
        respond pointOfInterest
    }
    // end::editMediaFile[]

    // tag::show[]
    @Transactional(readOnly = true)
    def show(Announcement pointOfInterest) {
        respond pointOfInterest
    }
    // end::show[]

    // tag::create[]
    @Transactional(readOnly = true)
    def create() {
        respond new Announcement(params)
    }
    // end::create[]

    // tag::uploadMediaFile[]
    def uploadMediaFile(MediaFileCommand cmd) {

        if (cmd.hasErrors()) {
            respond(cmd, model: [pointOfInterest: cmd], view: 'editMediaFile')
            return
        }

        def pointOfInterest = uploadAnnouncementMediaFileService.uploadMediaFile(cmd)
        if (pointOfInterest == null) {
            notFound()
            return
        }

        if (pointOfInterest.hasErrors()) {
            respond(pointOfInterest, model: [pointOfInterest: pointOfInterest], view: 'editMediaFile')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'announcement.label', default: 'Point of Interest'), pointOfInterest.id])
                redirect pointOfInterest
            }
            '*'{ respond pointOfInterest, [status: OK] }
        }
    }
    // end::uploadMediaFile[]

    // tag::save[]
    def save(NameCommand cmd) {
        if (cmd == null) {
            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond cmd.errors, model: [pointOfInterest: cmd], view: 'create'
            return
        }

        def pointOfInterest = AnnouncementGormService.save(cmd)

        if (pointOfInterest == null) {
            notFound()
            return
        }

        if (pointOfInterest.hasErrors()) {
            respond pointOfInterest.errors, model: [pointOfInterest: pointOfInterest], view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'announcement.label', default: 'Announcement'), pointOfInterest.id])
                redirect pointOfInterest
            }
            '*' { respond pointOfInterest, [status: CREATED] }
        }
    }
    // end::save[]

    // tag::delete[]
    def delete() {
        Long pointOfInterestId = params.long('id')
        if ( pointOfInterestId == null) {
            notFound()
            return
        }

        AnnouncementGormService.deleteById(pointOfInterestId)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'announcement.label', default: 'Announcement'), pointOfInterestId])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    // end::delete[]

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'announcement.label', default: 'Announcement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
