package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.HotSearch;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class HotSearchController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HotSearch.list(params), model:[hotSearchInstanceCount: HotSearch.count()]
    }

    def show(HotSearch hotSearchInstance) {
        respond hotSearchInstance
    }

    def create() {
        respond new HotSearch(params)
    }

    @Transactional
    def save(HotSearch hotSearchInstance) {
        if (hotSearchInstance == null) {
            notFound()
            return
        }

        if (hotSearchInstance.hasErrors()) {
            respond hotSearchInstance.errors, view:'create'
            return
        }

        hotSearchInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotSearch.label', default: 'HotSearch'), hotSearchInstance.id])
                redirect hotSearchInstance
            }
            '*' { respond hotSearchInstance, [status: CREATED] }
        }
    }

    def edit(HotSearch hotSearchInstance) {
        respond hotSearchInstance
    }

    @Transactional
    def update(HotSearch hotSearchInstance) {
        if (hotSearchInstance == null) {
            notFound()
            return
        }

        if (hotSearchInstance.hasErrors()) {
            respond hotSearchInstance.errors, view:'edit'
            return
        }

        hotSearchInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'HotSearch.label', default: 'HotSearch'), hotSearchInstance.id])
                redirect hotSearchInstance
            }
            '*'{ respond hotSearchInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(HotSearch hotSearchInstance) {

        if (hotSearchInstance == null) {
            notFound()
            return
        }

        hotSearchInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'HotSearch.label', default: 'HotSearch'), hotSearchInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotSearch.label', default: 'HotSearch'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
