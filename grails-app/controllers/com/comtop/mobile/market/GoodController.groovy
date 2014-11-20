package com.comtop.mobile.market

import com.comtop.mobile.market.util.FileUtils
import grails.converters.JSON
import grails.transaction.Transactional
import org.hibernate.collection.internal.PersistentSet

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class GoodController {

    FileUtils fileUtils

    GoodService goodService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    /**
     * 获取商品的信息
     * @param id 商品Id
     */
    def mGet(String id){
        Good good = Good.get(id)
        render good as JSON
    }

    /**
     *
     * @param pageSize
     * @param currentPage
     * @param type
     */
    def mFind(int pageSize,int currentPage,String type){
        def data
        data = goodService.find(pageSize, currentPage, type)
        render(data as JSON)
    }

    def mSave(){

    }

    def mUpdate(){

    }

    def mDelete(){

    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Good.list(params), model: [goodInstanceCount: Good.count()]
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

        goodInstance.code = "xxx"
        goodInstance.createTime = new Date()
        goodInstance.updateTime = new Date()
//		if (goodInstance.hasErrors()) {
//			println goodInstance.errors
//			respond goodInstance.errors, view:'create'
//			return
//		}
        List<GoodPicture> pgList = []
        4.times {
            def f = request.getFile('imgFile' + it)
            if (!f.isEmpty()) {
                GoodPicture gp = new GoodPicture()
                gp.imgName = f.getOriginalFilename()
                gp.indexOrder = it
                gp.save flush: true
                fileUtils.saveFile(f, gp.id)
                pgList.add(gp)
            }
        }

        goodInstance.pictures = pgList
        goodInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
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
            respond goodInstance.errors, view: 'edit'
            return
        }
        PersistentSet<GoodPicture> pgList = goodInstance.pictures
//		fileUtils.get
        println "${pgList.size()}"
        4.times {
            def f = request.getFile('imgFile' + it)
            if (!f.isEmpty()) {
                GoodPicture gp = new GoodPicture()
                gp.imgName = f.getOriginalFilename()
                gp.indexOrder = it
                gp.save flush: true
                fileUtils.saveFile(f, gp.id)
                pgList[it] = gp
            }
        }
        goodInstance.pictures = pgList

        goodInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
                redirect goodInstance
            }
            '*' { respond goodInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Good goodInstance) {

        if (goodInstance == null) {
            notFound()
            return
        }

        goodInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        params.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
