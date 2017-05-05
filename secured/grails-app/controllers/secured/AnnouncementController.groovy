package secured

import test.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class AnnouncementController {

    static allowedMethods = [save         : "POST",
                             update       : "PUT",
                             uploadMediaFile : 'POST',
                             delete       : "DELETE"]

    def springSecurityService
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
    def editMediaFile(Announcement announcement) {
        respond announcement
    }
    // end::editMediaFile[]

    // tag::show[]
    @Transactional(readOnly = true)
    def show(Announcement announcement) {
        respond announcement
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

        def announcement = uploadAnnouncementMediaFileService.uploadMediaFile(cmd)
        if (announcement == null) {
            notFound()
            return
        }

        if (announcement.hasErrors()) {
            respond(announcement, model: [pointOfInterest: announcement], view: 'editMediaFile')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.uploaded.message', args: [message(code: 'announcement.label', default: 'Annoucement'), announcement.id])
                redirect announcement
            }
            '*'{ respond announcement, [status: OK] }
        }
    }
    // end::uploadMediaFile[]

    // tag::save[]
    def save(NameCommand cmd) {

        if (isLoggedIn()) {
            cmd.author = springSecurityService.authentication.principal.username
        }

        if (cmd == null) {
            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond cmd.errors, model: [pointOfInterest: cmd], view: 'create'
            return
        }

        def announcement = AnnouncementGormService.save(cmd)

        if (announcement == null) {
            notFound()
            return
        }

        if (announcement.hasErrors()) {
            respond announcement.errors, model: [pointOfInterest: announcement], view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'announcement.label', default: 'Announcement'), announcement.id])
                redirect announcement
            }
            '*' { respond announcement, [status: CREATED] }
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
