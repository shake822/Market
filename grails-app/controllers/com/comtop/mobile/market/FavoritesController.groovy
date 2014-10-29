package com.comtop.mobile.market



import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.Favorites;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class FavoritesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Favorites.list(params), model:[favoritesInstanceCount: Favorites.count()]
    }

    def show(Favorites favoritesInstance) {
        respond favoritesInstance
    }

    def create() {
        respond new Favorites(params)
    }

    @Transactional
    def save(Favorites favoritesInstance) {
        if (favoritesInstance == null) {
            notFound()
            return
        }

        if (favoritesInstance.hasErrors()) {
            respond favoritesInstance.errors, view:'create'
            return
        }

        favoritesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'favorites.label', default: 'Favorites'), favoritesInstance.id])
                redirect favoritesInstance
            }
            '*' { respond favoritesInstance, [status: CREATED] }
        }
    }

    def edit(Favorites favoritesInstance) {
        respond favoritesInstance
    }

    @Transactional
    def update(Favorites favoritesInstance) {
        if (favoritesInstance == null) {
            notFound()
            return
        }

        if (favoritesInstance.hasErrors()) {
            respond favoritesInstance.errors, view:'edit'
            return
        }

        favoritesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Favorites.label', default: 'Favorites'), favoritesInstance.id])
                redirect favoritesInstance
            }
            '*'{ respond favoritesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Favorites favoritesInstance) {

        if (favoritesInstance == null) {
            notFound()
            return
        }

        favoritesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Favorites.label', default: 'Favorites'), favoritesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'favorites.label', default: 'Favorites'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
