package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.GoodPicture;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodPictureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GoodPicture.list(params), model:[goodPictureInstanceCount: GoodPicture.count()]
    }

    def show(GoodPicture goodPictureInstance) {
        respond goodPictureInstance
    }

    def create() {
        respond new GoodPicture(params)
    }

    @Transactional
    def save(GoodPicture goodPictureInstance) {
        if (goodPictureInstance == null) {
            notFound()
            return
        }

        if (goodPictureInstance.hasErrors()) {
            respond goodPictureInstance.errors, view:'create'
            return
        }

        goodPictureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'goodPicture.label', default: 'GoodPicture'), goodPictureInstance.id])
                redirect goodPictureInstance
            }
            '*' { respond goodPictureInstance, [status: CREATED] }
        }
    }

    def edit(GoodPicture goodPictureInstance) {
        respond goodPictureInstance
    }

    @Transactional
    def update(GoodPicture goodPictureInstance) {
        if (goodPictureInstance == null) {
            notFound()
            return
        }

        if (goodPictureInstance.hasErrors()) {
            respond goodPictureInstance.errors, view:'edit'
            return
        }

        goodPictureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GoodPicture.label', default: 'GoodPicture'), goodPictureInstance.id])
                redirect goodPictureInstance
            }
            '*'{ respond goodPictureInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GoodPicture goodPictureInstance) {

        if (goodPictureInstance == null) {
            notFound()
            return
        }

        goodPictureInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GoodPicture.label', default: 'GoodPicture'), goodPictureInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodPicture.label', default: 'GoodPicture'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
