package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantGroovyUtils
import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.FileUtils
import com.comtop.mobile.market.util.JsonHelper
import grails.converters.JSON
import grails.transaction.Transactional

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
    def mGet(String id) {
        def data = goodService.getGood(id)
        render JsonHelper.onSuccessBody("${data}")
    }

    /**
     *
     * @param pageSize
     * @param currentPage
     * @param status
     */
    def mFind(int pageSize, int currentPage, String status) {
        def data
        data = goodService.findAll(pageSize, currentPage, status) as JSON
        render JsonHelper.onSuccessBody("${data}")
    }

    def mSave() {
        Good good = new Good()
        good.id = params.id
    }

    def mUpdate() {

    }

    def mDelete() {

    }


    def index(Integer max) {
        initGoodKeyValues()
        params.max = Math.min(max ?: 10, 100)
        respond Good.list(params), model: [goodInstanceCount: Good.count()]
    }

    private void initGoodKeyValues() {
        flash.goodStatus = ConstantGroovyUtils.goodStatus
        flash.goodRecency = ConstantGroovyUtils.goodRecency
    }

    def show(Good goodInstance) {
        goodInstance.recency = ConstantGroovyUtils.goodRecency.get(new Integer(goodInstance.recency))
        goodInstance.status = ConstantGroovyUtils.goodStatus.get(new Integer(goodInstance.status))
        respond goodInstance
    }

    def create() {
        initGoodKeyValues()
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
        initGoodKeyValues()
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
        List<GoodPicture> pgList = []
        goodInstance.pictures?.each {
            pgList[it.indexOrder] = it
        }
        4.times {
            def f = request.getFile('imgFile' + it)
            if (!f.isEmpty()) {
                println "update ${it}"
                GoodPicture gp = pgList[it] ?: new GoodPicture()
                gp.imgName = f.getOriginalFilename()
                gp.indexOrder = it
                gp.save flush: true
                fileUtils.saveFile(f, gp.id)
                pgList[it] = gp
            }
        }
        if (pgList.size() > 0) {
            goodInstance.pictures = pgList
        }


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
