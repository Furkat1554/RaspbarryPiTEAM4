package secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RaspberryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Raspberry.list(params), model:[raspberryCount: Raspberry.count()]
    }

    def show(Raspberry raspberry) {
        respond raspberry
    }

    def create() {
        respond new Raspberry(params)
    }

    @Transactional
    def save(Raspberry raspberry) {
        if (raspberry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (raspberry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond raspberry.errors, view:'create'
            return
        }

        raspberry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'raspberry.label', default: 'Raspberry'), raspberry.id])
                redirect raspberry
            }
            '*' { respond raspberry, [status: CREATED] }
        }
    }

    def edit(Raspberry raspberry) {
        respond raspberry
    }

    @Transactional
    def update(Raspberry raspberry) {
        if (raspberry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (raspberry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond raspberry.errors, view:'edit'
            return
        }

        raspberry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'raspberry.label', default: 'Raspberry'), raspberry.id])
                redirect raspberry
            }
            '*'{ respond raspberry, [status: OK] }
        }
    }

    @Transactional
    def delete(Raspberry raspberry) {

        if (raspberry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        raspberry.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'raspberry.label', default: 'Raspberry'), raspberry.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'raspberry.label', default: 'Raspberry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
