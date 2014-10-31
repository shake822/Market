package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.Classify;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClassifyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def init(){
		Classify classify = new Classify()
		classify.code="00"
		classify.name="顶级"
		classify.parent.id="-1"
		classify.save  flush:true
	}
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Classify.list(params), model:[classifyInstanceCount: Classify.count()]
    }

    def show(Classify classifyInstance) {
        respond classifyInstance
    }

    def create() {
        respond new Classify(params)
    }

    @Transactional
    def save(Classify classifyInstance) {
        if (classifyInstance == null) {
            notFound()
            return
        }

        if (classifyInstance.hasErrors()) {
            respond classifyInstance.errors, view:'create'
            return
        }

        classifyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'classify.label', default: 'Classify'), classifyInstance.id])
                redirect classifyInstance
            }
            '*' { respond classifyInstance, [status: CREATED] }
        }
    }

    def edit(Classify classifyInstance) {
        respond classifyInstance
    }

    @Transactional
    def update(Classify classifyInstance) {
        if (classifyInstance == null) {
            notFound()
            return
        }

        if (classifyInstance.hasErrors()) {
            respond classifyInstance.errors, view:'edit'
            return
        }

        classifyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Classify.label', default: 'Classify'), classifyInstance.id])
                redirect classifyInstance
            }
            '*'{ respond classifyInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Classify classifyInstance) {

        if (classifyInstance == null) {
            notFound()
            return
        }

        classifyInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Classify.label', default: 'Classify'), classifyInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'classify.label', default: 'Classify'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
