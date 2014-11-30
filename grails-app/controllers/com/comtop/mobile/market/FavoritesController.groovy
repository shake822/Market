package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.JsonHelper
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray

import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.Favorites;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class FavoritesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Favorites.list(params), model: [favoritesInstanceCount: Favorites.count()]
    }

    def show(Favorites favoritesInstance) {
        respond favoritesInstance
    }

    def create() {
        respond new Favorites(params)
    }

    @Transactional
    def mDelete() {
        Favorites favorites = Favorites.get(params.id)

        try {

            favorites.delete()

        } catch (Exception e) {
            render JsonHelper.onError("服务器内部错误")
            return
        }
        render JsonHelper.onSuccessMessage("删除成功")

    }


    def mGetMyFavorites() {
        def list = Favorites.findAll() {
            user {
                eq("id", params.id)
            }

            eq("deleteFlag", false)
        }

        def data = list.collect()
                {

                    [
                            id        : it.id,
                            createTime: it.createTime,
                            goodId    : it.goodId,
                            userId    : it.userId,
                            deleteFlag: it.deleteFlag
                    ]
                } as JSON

        render JsonHelper.onSuccessBody("${data}")

    }

    @Transactional
    def mSave() {
        Favorites favorites = new Favorites()

        favorites.createTime = new Date()
        favorites.deleteFlag = params.deleteFlag ?: false
        favorites.user = User.get(params.userid)
        favorites.good = Good.get(params.gooid)

        try {
            def favoritesSaved = favorites.save flush: true
            if (favoritesSaved == null) {
                render JsonHelper.onError("参数有误")
                return
            }
        } catch (Exception e) {
            render JsonHelper.onError("服务器内部错误")
            return
        }
        render JsonHelper.onSuccessBody("添加成功")

    }

    @Transactional
    def save(Favorites favoritesInstance) {
        if (favoritesInstance == null) {
            notFound()
            return
        }

        if (favoritesInstance.hasErrors()) {
            respond favoritesInstance.errors, view: 'create'
            return
        }

        favoritesInstance.save flush: true

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
            respond favoritesInstance.errors, view: 'edit'
            return
        }

        favoritesInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Favorites.label', default: 'Favorites'), favoritesInstance.id])
                redirect favoritesInstance
            }
            '*' { respond favoritesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Favorites favoritesInstance) {

        if (favoritesInstance == null) {
            notFound()
            return
        }

        favoritesInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Favorites.label', default: 'Favorites'), favoritesInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'favorites.label', default: 'Favorites'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
