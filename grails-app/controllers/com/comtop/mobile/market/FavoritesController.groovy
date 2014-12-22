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

    def favoritesService;

    UtilsService utilsService

    @Transactional
    def mDelete() {
        Favorites favorites = Favorites.get(params.id)
        try {
            favorites.deleteFlag = true
            favorites.save flush: true
        } catch (Exception e) {
            render JsonHelper.onError("服务器内部错误")
            return
        }
        render JsonHelper.onSuccessMessage("删除成功")

    }

    /**
     * 是否搜藏物品
     * @param goodId 物品ID
     * @return
     */
    def mIsFavorites(String goodId){
        def favoritesId
        def list =  Favorites.findAll(){
            user {
                eq("id", session.user.id)
            }
            good{
                eq("id", goodId)
            }
            eq("deleteFlag", false)
        }
        if(list.size() >0){
            favoritesId = list.get(0).id
        }
       render JsonHelper.onSuccessMessage(favoritesId)
    }

    /**
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    def mFindMyFavorites(String pageSize, String currentPage) {
        println "123123   ${pageSize} === ${currentPage}"

        def data = utilsService.findAll(pageSize,currentPage){
            queryParams ->
                def list =  Favorites.findAll(queryParams){
                    user {
                        eq("id", session.user.id)
                    }
                    eq("deleteFlag", false)
                }
                def data = list.collect(){
                    def pictureId = null
                    def pictureIndex = 4
                    it.good?.pictures?.each() {
                        if (pictureIndex > it.indexOrder) {
                            pictureId = ConstantUtils.IMAGE_URL + it.id
                            pictureIndex = it.indexOrder
                        }

                        if (it.indexOrder == 0) {
                            return
                        }
                    }
                    [
                            id        : it.id,
                            createTime: it.createTime,
                            good   : [
                                    id         : it.good.id,
                                    classify   :  it.good.classify,
                                    userId     :  it.good.userId,
                                    description:  it.good.description,
                                    deleteFlag :  it.good.deleteFlag,
                                    name       :  it.good.name,
                                    price      :  it.good.price,
                                    recency    :  it.good.recency,
                                    status     :  it.good.status,
                                    updateTime : it.good.updateTime,
                                    transStatus:  it.good.transStatus,
                                    code       :  it.good.code,
                                    picture    : pictureId
                            ]
                    ]
                }
                data
        }

        render JsonHelper.onSuccessBody("${data as JSON}")
    }

    @Transactional
    def mSave() {
        Favorites favorites = new Favorites()
        favorites.createTime = new Date()
        favorites.deleteFlag = params.deleteFlag ?: false
        favorites.user = session.user
        favorites.good = Good.get(params.goodId)
        Favorites favoritesSaved
        try {
            favoritesSaved = favorites.save flush: true
            if (favoritesSaved == null) {
                render JsonHelper.onError("参数有误")
                return
            }
        } catch (Exception e) {
            render JsonHelper.onError("服务器内部错误")
            return
        }

        render JsonHelper.onSuccessMessage("${favoritesSaved.id}")
    }


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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'favorites.label', default: 'Favorites'), favoritesInstance.id])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'favorites.label', default: 'Favorites'), favoritesInstance.id])
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
