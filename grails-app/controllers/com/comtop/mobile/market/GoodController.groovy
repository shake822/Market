package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.Good;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Good.list(params), model:[goodInstanceCount: Good.count()]
    }

    def show(Good goodInstance) {
        respond goodInstance
    }

    def create() {
        respond new Good(params)
    }

    @Transactional
    def save(Good goodInstance) {
        if (goodInstance == null) {
            notFound()
            return
        }

        if (goodInstance.hasErrors()) {
            respond goodInstance.errors, view:'create'
            return
        }

        goodInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'good.label', default: 'Good'), goodInstance.id])
                redirect goodInstance
            }
            '*' { respond goodInstance, [status: CREATED] }
        }
    }

    def edit(Good goodInstance) {
        respond goodInstance
    }

    @Transactional
    def update(Good goodInstance) {
        if (goodInstance == null) {
            notFound()
            return
        }

        if (goodInstance.hasErrors()) {
            respond goodInstance.errors, view:'edit'
            return
        }

        goodInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Good.label', default: 'Good'), goodInstance.id])
                redirect goodInstance
            }
            '*'{ respond goodInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Good goodInstance) {

        if (goodInstance == null) {
            notFound()
            return
        }

        goodInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Good.label', default: 'Good'), goodInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'good.label', default: 'Good'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
