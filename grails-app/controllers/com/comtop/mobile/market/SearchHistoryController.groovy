package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.SearchHistory;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class SearchHistoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SearchHistory.list(params), model:[searchHistoryInstanceCount: SearchHistory.count()]
    }

    def show(SearchHistory searchHistoryInstance) {
        respond searchHistoryInstance
    }

    def create() {
        respond new SearchHistory(params)
    }

    @Transactional
    def save(SearchHistory searchHistoryInstance) {
        if (searchHistoryInstance == null) {
            notFound()
            return
        }

        if (searchHistoryInstance.hasErrors()) {
            respond searchHistoryInstance.errors, view:'create'
            return
        }

        searchHistoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchHistory.label', default: 'SearchHistory'), searchHistoryInstance.id])
                redirect searchHistoryInstance
            }
            '*' { respond searchHistoryInstance, [status: CREATED] }
        }
    }

    def edit(SearchHistory searchHistoryInstance) {
        respond searchHistoryInstance
    }

    @Transactional
    def update(SearchHistory searchHistoryInstance) {
        if (searchHistoryInstance == null) {
            notFound()
            return
        }

        if (searchHistoryInstance.hasErrors()) {
            respond searchHistoryInstance.errors, view:'edit'
            return
        }

        searchHistoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SearchHistory.label', default: 'SearchHistory'), searchHistoryInstance.id])
                redirect searchHistoryInstance
            }
            '*'{ respond searchHistoryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SearchHistory searchHistoryInstance) {

        if (searchHistoryInstance == null) {
            notFound()
            return
        }

        searchHistoryInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SearchHistory.label', default: 'SearchHistory'), searchHistoryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchHistory.label', default: 'SearchHistory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
